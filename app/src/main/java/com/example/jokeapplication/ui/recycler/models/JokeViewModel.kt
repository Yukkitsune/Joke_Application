package com.example.jokeapplication.ui.recycler.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokeapplication.R
import com.example.jokeapplication.data.Joke

class JokeViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    init {
        loadData()
    }

    private fun loadData() {
        _jokes.value = listOf(
            Joke(
                category = "Holiday",
                question = "What does Santa suffer from if he gets stuck in a chimney?",
                answer = "Claustrophobia!",
                image = R.drawable.fireworks
            ),
            Joke(
                category = "Animals",
                question = "How do you count cows?",
                answer = "With a cowculator!",
                image = R.drawable.paw
            ),
            Joke(
                category = "Food",
                question = "What is the best time to eat Mexican food?",
                answer = "Juan o'clock!",
                image = R.drawable.restaurant
            ),
            Joke(
                category = "Tech",
                question = "Why do programmers always mix up Halloween and Christmas?",
                answer = "Because Oct 31 == Dec 25!",
                image = R.drawable.tech
            ),
            Joke(
                category = "Puns",
                question = "I’m reading a book on anti-gravity.",
                answer = "It’s impossible to put down!",
                image = R.drawable.puns
            ),
            Joke(
                category = "Study",
                question = "I heard the band Europe wrote a song about the days leading to the end-of-semester exams?",
                answer = "It was the finals countdown!",
                image = R.drawable.study
            ),
            Joke(
                category = "Space",
                question = "Why did the cow go to space?",
                answer = "To see the moooon!",
                image = R.drawable.space
            )
        )
    }
}