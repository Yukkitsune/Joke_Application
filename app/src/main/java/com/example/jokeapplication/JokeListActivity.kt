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
                "Holiday",
                "What does Santa suffer from if he gets stuck in a chimney?",
                "Claustrophobia!"
            ),
            Joke("Animals", "How do you count cows?", "With a cowculator!"),
            Joke("Food", "What is the best time to eat Mexican food?", "Juan o'clock!"),
            Joke(
                "Tech",
                "Why do programmers always mix up Halloween and Christmas?",
                "Because Oct 31 == Dec 25!"
            ),
            Joke("Puns", "I’m reading a book on anti-gravity.", "It’s impossible to put down!"),
            Joke(
                "Study",
                "I heard the band Europe wrote a song about the days leading to the end-of-semester exams?",
                "It was the finals countdown!"
            ),
            Joke("Space", "Why did the cow go to space?", "To see the moooon!")
        )

        val recyclerView: RecyclerView = findViewById(R.id.main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = JokeAdapter(jokes)
    }
}