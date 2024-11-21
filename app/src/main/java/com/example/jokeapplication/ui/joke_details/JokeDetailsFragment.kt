package com.example.jokeapplication.ui.joke_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.FragmentJokeDetailsBinding

class JokeDetailsFragment : Fragment(R.layout.fragment_joke_details) {
    private val binding: FragmentJokeDetailsBinding by viewBinding(FragmentJokeDetailsBinding::bind)
    private val args: JokeDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCategory.text = args.category
        binding.tvQuestion.text = args.question
        binding.tvAnswer.text = args.answer
    }
}