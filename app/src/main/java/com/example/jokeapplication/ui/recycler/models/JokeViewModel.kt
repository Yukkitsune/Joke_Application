package com.example.jokeapplication.ui.recycler.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapplication.R
import com.example.jokeapplication.api.JokeDetail
import com.example.jokeapplication.api.RetrofitInstance
import com.example.jokeapplication.data.Joke
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Locale

class JokeViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val mutableProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> = mutableProgressLiveData

    init {
        loadData()
    }

    private fun loadData() {
        _jokes.postValue(
            listOf(
                Joke(
                    category = "Christmas",
                    question = "What does Santa suffer from if he gets stuck in a chimney?",
                    answer = "Claustrophobia!",
                    source = "User",
                    image = R.drawable.fireworks
                ), Joke(
                    category = "Animals",
                    question = "How do you count cows?",
                    answer = "With a cowculator!",
                    source = "User",
                    image = R.drawable.paw
                ), Joke(
                    category = "Food",
                    question = "What is the best time to eat Mexican food?",
                    answer = "Juan o'clock!",
                    source = "User",
                    image = R.drawable.restaurant
                ), Joke(
                    category = "Programming",
                    question = "Why do programmers always mix up Halloween and Christmas?",
                    answer = "Because Oct 31 == Dec 25!",
                    source = "User",
                    image = R.drawable.tech
                ), Joke(
                    category = "Pun",
                    question = "I’m reading a book on anti-gravity.",
                    answer = "It’s impossible to put down!",
                    source = "User",
                    image = R.drawable.puns
                ), Joke(
                    category = "Study",
                    question = "I heard the band Europe wrote a song about the days leading to the end-of-semester exams?",
                    answer = "It was the finals countdown!",
                    source = "User",
                    image = R.drawable.study
                ), Joke(
                    category = "Space",
                    question = "Why did the cow go to space?",
                    answer = "To see the moooon!",
                    source = "User",
                    image = R.drawable.space
                )
            )
        )
    }

    fun addJoke(joke: Joke) {
        val currentList = _jokes.value.orEmpty().toMutableList()
        currentList.add(joke)
        _jokes.value = currentList
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Something went wrong:, $exception")
        mutableProgressLiveData.postValue(false)
    }

    fun fetchJokes() {
        viewModelScope.launch(exceptionHandler) {
            try {
                mutableProgressLiveData.postValue(true)
                val response = RetrofitInstance.api.getRandomJoke()

                if (!response.error) {
                    val newJokes = response.jokes.mapNotNull { joke ->
                        createInternetJoke(joke)
                    }
                    val updatedJokes = (_jokes.value.orEmpty() + newJokes)
                    _jokes.postValue(updatedJokes)
                }
            } catch (e: Exception) {
                println("Error fetching jokes: $e")
            } finally {
                mutableProgressLiveData.postValue(false)
            }
        }
    }

    private fun isJokeInList(joke: JokeDetail): Boolean {
        val currentList = _jokes.value.orEmpty().toMutableList()
        return currentList.any { it.question == joke.setup }
    }

    private fun createInternetJoke(joke: JokeDetail): Joke? {
        if (!isJokeInList(joke)) {
            return Joke(
                category = joke.category,
                question = joke.setup,
                answer = joke.delivery,
                source = "Internet",
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
}
