package com.example.jokeapplication.ui.recycler.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapplication.R
import com.example.jokeapplication.data.Joke
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val mutableProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> = mutableProgressLiveData

    init {
        mutableProgressLiveData.value = true
        loadData()
    }

    private fun loadData() {
        _jokes.postValue(
            listOf(
                Joke(
                    category = "Holiday",
                    question = "What does Santa suffer from if he gets stuck in a chimney?",
                    answer = "Claustrophobia!",
                    image = R.drawable.fireworks
                ), Joke(
                    category = "Animals",
                    question = "How do you count cows?",
                    answer = "With a cowculator!",
                    image = R.drawable.paw
                ), Joke(
                    category = "Food",
                    question = "What is the best time to eat Mexican food?",
                    answer = "Juan o'clock!",
                    image = R.drawable.restaurant
                ), Joke(
                    category = "Tech",
                    question = "Why do programmers always mix up Halloween and Christmas?",
                    answer = "Because Oct 31 == Dec 25!",
                    image = R.drawable.tech
                ), Joke(
                    category = "Puns",
                    question = "I’m reading a book on anti-gravity.",
                    answer = "It’s impossible to put down!",
                    image = R.drawable.puns
                ), Joke(
                    category = "Study",
                    question = "I heard the band Europe wrote a song about the days leading to the end-of-semester exams?",
                    answer = "It was the finals countdown!",
                    image = R.drawable.study
                ), Joke(
                    category = "Space",
                    question = "Why did the cow go to space?",
                    answer = "To see the moooon!",
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

    suspend fun fetchJokes() {
        viewModelScope.launch(exceptionHandler) {
            try {
                mutableProgressLiveData.postValue(true)
                delay(1500L)
                _jokes.postValue(_jokes.value)


            } catch (e: Exception) {
                println("Error: $e")
            } finally {
                mutableProgressLiveData.postValue(false)
            }
        }

    }

}
