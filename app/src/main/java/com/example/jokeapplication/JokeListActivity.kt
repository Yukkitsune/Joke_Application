package com.example.jokeapplication

import JokeAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.ActivityJokeListBinding

class JokeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJokeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jokes = listOf(
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

        val recyclerView: RecyclerView = findViewById(R.id.main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = JokeAdapter(jokes)
    }
}