package com.example.jokeapplication.ui.joke_list

import android.os.Bundle
import android.view.View
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
            joke.image?.let { image ->
                val action = JokeListFragmentDirections.actionJokeListFragmentToJokeDetailsFragment(
                    joke.category, joke.question, joke.answer, joke.source, image
                )
                findNavController().navigate(action)
            } ?: run {
                println("Image is null")
            }
        }

        val recyclerView: RecyclerView = binding.main
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        jokeViewModel.progressLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbLoadingJokes.visibility = View.VISIBLE
            } else {
                binding.pbLoadingJokes.visibility = View.GONE
            }
        }

        jokeViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            if (jokes.isNullOrEmpty()) {
                binding.tvEmptyJokes.visibility = View.VISIBLE
                binding.ivEmptyJokes.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                binding.tvEmptyJokes.visibility = View.GONE
                binding.ivEmptyJokes.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.submitList(jokes)

            }
        }
        binding.btnAddJoke.setOnClickListener {
            val action = JokeListFragmentDirections.actionJokeListFragmentToJokeAddFragment()
            findNavController().navigate(action)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!jokeViewModel.progressLiveData.value!! &&
                    (visibleItemCount + firstVisibleItemPosition >= totalItemCount) &&
                    firstVisibleItemPosition >= 0
                ) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        jokeViewModel.fetchJokes()
                    }
                }
            }
        })
    }

}
