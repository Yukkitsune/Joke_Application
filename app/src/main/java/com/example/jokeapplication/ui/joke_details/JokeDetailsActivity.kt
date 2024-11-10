package com.example.jokeapplication.ui.joke_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jokeapplication.databinding.ActivityJokeDetailsBinding

class JokeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJokeDetailsBinding
    private val jokeCategory: String by lazy { intent.getStringExtra(CATEGORY).orEmpty() }
    private val jokeQuestion: String by lazy { intent.getStringExtra(QUESTION).orEmpty() }
    private val jokeAnswer: String by lazy { intent.getStringExtra(ANSWER).orEmpty() }

    companion object {
        private const val CATEGORY = "EXTRA_CATEGORY"
        private const val QUESTION = "EXTRA_QUESTION"
        private const val ANSWER = "EXTRA_ANSWER"

        fun createIntent(context: Context, category: String, question: String, answer: String) =
            Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(CATEGORY, category)
                putExtra(QUESTION, question)
                putExtra(ANSWER, answer)
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryTextView.text = jokeCategory
        binding.questionTextView.text = jokeQuestion
        binding.answerTextView.text = jokeAnswer
    }
}