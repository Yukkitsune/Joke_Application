package com.example.jokeapplication.ui.joke_details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.ActivityJokeDetailsBinding

class JokeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJokeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getStringExtra("EXTRA_CATEGORY")
        val question = intent.getStringExtra("EXTRA_QUESTION")
        val answer = intent.getStringExtra("EXTRA_ANSWER")
        binding.categoryTextView.text = category
        binding.questionTextView.text = question
        binding.answerTextView.text = answer
    }
}