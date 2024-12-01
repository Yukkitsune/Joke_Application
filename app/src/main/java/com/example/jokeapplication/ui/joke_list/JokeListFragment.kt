package com.example.jokeapplication.ui.joke_list

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.FragmentJokeListBinding
import com.example.jokeapplication.ui.recycler.adapters.JokeAdapter
import com.example.jokeapplication.ui.recycler.models.JokeViewModel
import kotlinx.coroutines.launch


class JokeListFragment : Fragment(R.layout.fragment_joke_list) {

    private val binding: FragmentJokeListBinding by viewBinding(FragmentJokeListBinding::bind)
    private val jokeViewModel: JokeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = JokeAdapter { joke ->
            val action = JokeListFragmentDirections.actionJokeListFragmentToJokeDetailsFragment(
                    joke.category, joke.question, joke.answer
                )
            findNavController().navigate(action)
        }

        val recyclerView: RecyclerView = binding.main
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        jokeViewModel.progressLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoadingJokes.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                binding.tvEmptyJokes.visibility = View.GONE
                binding.ivEmptyJokes.visibility = View.GONE
                recyclerView.visibility = View.GONE
            }
        }

        jokeViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            if (jokes.isNullOrEmpty()) {
                binding.tvEmptyJokes.visibility = View.VISIBLE
                binding.ivEmptyJokes.visibility = View.VISIBLE
                binding.btnAddJoke.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                binding.tvEmptyJokes.visibility = View.GONE
                binding.ivEmptyJokes.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                binding.btnAddJoke.visibility = View.VISIBLE
                adapter.submitList(jokes)

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            jokeViewModel.fetchJokes()
        }
        val btnAddJoke: Button = binding.btnAddJoke
        btnAddJoke.setOnClickListener {
            val action = JokeListFragmentDirections.actionJokeListFragmentToJokeAddFragment()
            findNavController().navigate(action)
        }

    }

}
