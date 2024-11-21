package com.example.jokeapplication.ui.joke_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.FragmentJokeListBinding
import com.example.jokeapplication.ui.recycler.adapters.JokeAdapter
import com.example.jokeapplication.ui.recycler.models.JokeViewModel


class JokeListFragment : Fragment(R.layout.fragment_joke_list) {

    private val binding: FragmentJokeListBinding by viewBinding(FragmentJokeListBinding::bind)
    private val jokeViewModel: JokeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = JokeAdapter { joke ->
            val action = JokeListFragmentDirections
                .actionJokeListFragmentToJokeDetailsFragment(
                    joke.category,
                    joke.question,
                    joke.answer
                )
            findNavController().navigate(action)
        }

        val recyclerView: RecyclerView = binding.main
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        jokeViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            adapter.submitList(jokes)
        }

    }

}