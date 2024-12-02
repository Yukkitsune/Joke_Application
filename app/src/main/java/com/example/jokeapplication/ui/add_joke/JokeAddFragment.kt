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
import java.util.Locale


class JokeAddFragment : Fragment(R.layout.fragment_joke_add) {

    private lateinit var binding: FragmentJokeAddBinding
    private val jokeViewModel: JokeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokeAddBinding.bind(view)
        binding.btnSubmitJoke.setOnClickListener {
            val userJoke = createUserJoke()
            if (userJoke.category.isEmpty() || userJoke.question.isEmpty() || userJoke.answer.isEmpty()) {
                Toast.makeText(context, "Please, fill the fields.", Toast.LENGTH_SHORT).show()
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    jokeViewModel.addUserJoke(userJoke)
                    Toast.makeText(context, "Joke added successfully!", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }

            }
        }
    }

    private fun createUserJoke(): Joke {
        val category = binding.etAddCategory.text.toString().trim()
        val question = binding.etAddQuestion.text.toString().trim()
        val answer = binding.etAddAnswer.text.toString().trim()
        val source = "User"
        val image = when (category.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
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

        return Joke(
            category = category,
            question = question,
            answer = answer,
            source = source,
            image = image
        )
    }
}
