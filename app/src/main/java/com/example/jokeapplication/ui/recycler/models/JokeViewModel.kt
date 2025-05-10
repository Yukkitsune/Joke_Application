package com.example.jokeapplication.ui.recycler.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jokeapplication.R
import com.example.jokeapplication.api.JokeDetail
import com.example.jokeapplication.api.RetrofitInstance
import com.example.jokeapplication.data.CachedJokeEntity
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.data.JokeRepository
import com.example.jokeapplication.data.db.JokeDatabase
import com.example.jokeapplication.data.toJoke
import com.example.jokeapplication.data.toUserJokeEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit

class JokeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = JokeDatabase.getDatabase(application)
    private val jokeDao = database.jokeDao()
    private val jokeRepository = JokeRepository(jokeDao)

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val mutableProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> = mutableProgressLiveData
    val networkError = MutableLiveData<String?>()
    private var hasShownCacheMessage = false

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val validTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(24)
            try {
                val cachedJokes = jokeRepository.getCachedJokes(validTime)

                if (cachedJokes.isNotEmpty()) {
                    _jokes.postValue(cachedJokes.map { it.toJoke() })
                    networkError.postValue("Loaded jokes from cache.")
                } else {
                    fetchJokesFromNetwork(validTime)
                }
            } catch (e: Exception) {
                networkError.postValue("Failed to load jokes. Check your connection.")
                if (!hasShownCacheMessage) {
                    networkError.postValue("Network unavailable. Loading cached jokes.")
                    loadCachedJokes(validTime)
                    hasShownCacheMessage = true
                }
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Something went wrong:, $exception")
        mutableProgressLiveData.postValue(false)
    }

    private suspend fun fetchJokesFromNetwork(validTime: Long) {
        viewModelScope.launch(exceptionHandler) {
            try {
                mutableProgressLiveData.postValue(true)
                val response = RetrofitInstance.api.getRandomJoke()

                if (!response.error)  {
                    val newJokes = response.jokes.mapNotNull { joke ->
                        createCachedJoke(joke)
                    }
                    if (newJokes.isNotEmpty()) {
                        jokeRepository.saveCachedJokes(newJokes)
                        jokeRepository.deleteOldCachedJokes(validTime)
                        val userJokes = jokeRepository.getUserJokes().map { it.toJoke() }
                        val currentJokes = _jokes.value.orEmpty()
                        val newJokesConverted = newJokes.map { it.toJoke() }
                        val allJokes =
                            (userJokes + currentJokes + newJokesConverted).distinctBy { it.question }
                        _jokes.postValue(allJokes)
                    }
                }
            } catch (e: Exception) {
                networkError.postValue("Network unavailable.")
                loadCachedJokes(validTime)
            } finally {
                mutableProgressLiveData.postValue(false)
            }
        }
    }

    private fun createCachedJoke(joke: JokeDetail): CachedJokeEntity? {
        if (!isJokeInList(joke)) {
            return CachedJokeEntity(
                id = joke.id,
                category = joke.category,
                question = joke.setup,
                answer = joke.delivery,
                source = "Internet",
                timestamp = System.currentTimeMillis(),
                image = when (joke.category.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                }) {
                    "Christmas" -> R.drawable.fireworks
                    "Animals" -> R.drawable.paw
                    "Food" -> R.drawable.restaurant
                    "Programming" -> R.drawable.tech
                    "Pun" -> R.drawable.puns
                    "Study" -> R.drawable.study
                    "Space" -> R.drawable.space
                    "Spooky" -> R.drawable.spooky
                    "Dark" -> R.drawable.dark
                    "Misc" -> R.drawable.misc
                    else -> R.drawable.question_sign
                }
            )
        }
        return null
    }

    private suspend fun loadCachedJokes(validTime: Long) {
        val cachedJokes = jokeRepository.getCachedJokes(validTime)
        val userJokes = jokeRepository.getUserJokes()
        val cachedJokesConverted = cachedJokes.map { it.toJoke() }
        val userJokesConverted = userJokes.map { it.toJoke() }
        val allJokes = (cachedJokesConverted + userJokesConverted).distinctBy { it.question }
        if (allJokes.isNotEmpty()) {
            _jokes.postValue(allJokes)
        } else {
            _jokes.postValue(emptyList())
            networkError.postValue("No cached jokes available.")
        }
    }

    fun fetchMoreJokes() {
        viewModelScope.launch {
            val validTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(24)
            fetchJokesFromNetwork(validTime)
        }
    }

    private fun isJokeInList(joke: JokeDetail): Boolean {
        val currentList = _jokes.value.orEmpty().toMutableList()
        return currentList.any { it.question == joke.setup }
    }

    fun addUserJoke(joke: Joke) {
        viewModelScope.launch {
            val jokeEntity = joke.toUserJokeEntity()
            jokeRepository.addUserJoke(jokeEntity)

            val currentJokes = _jokes.value.orEmpty()
            _jokes.postValue(currentJokes + joke)
        }
    }


}
