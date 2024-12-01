package com.example.jokeapplication.ui.add_joke

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokeapplication.R
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.FragmentJokeAddBinding
import com.example.jokeapplication.ui.recycler.models.JokeViewModel
import kotlinx.coroutines.launch


class JokeAddFragment : Fragment(R.layout.fragment_joke_add) {

    private lateinit var binding: FragmentJokeAddBinding
    private val jokeViewModel: JokeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokeAddBinding.bind(view)
        binding.btnSubmitJoke.setOnClickListener {
            val category = binding.etAddCategory.text.toString().trim()
            val question = binding.etAddQuestion.text.toString().trim()
            val answer = binding.etAddAnswer.text.toString().trim()
            val image = when (category) {
                "Holiday" -> R.drawable.fireworks
                "Study" -> R.drawable.study
                "Animals" -> R.drawable.paw
                "Food" -> R.drawable.restaurant
                "Tech" -> R.drawable.tech
                "Puns" -> R.drawable.puns
                "Space" -> R.drawable.space
                else -> R.drawable.question_sign
            }
            if (category.isEmpty() || question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(context, "Please, fill the fields.", Toast.LENGTH_SHORT).show()
            } else {
                val newJoke = Joke(
                    category = category, question = question, answer = answer, image = image
                )
                viewLifecycleOwner.lifecycleScope.launch {
                    jokeViewModel.addJoke(newJoke)
                    Toast.makeText(context, "Joke added successfully!", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }

            }
        }
    }
}
