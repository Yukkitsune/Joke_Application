package com.example.jokeapplication

import com.example.jokeapplication.ui.recycler.adapters.JokeAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.databinding.ActivityJokeListBinding
import com.example.jokeapplication.ui.recycler.models.JokeViewModel

class JokeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeListBinding
    private val jokeViewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView: RecyclerView = binding.main
        val adapter = JokeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        jokeViewModel.jokes.observe(this) { jokes ->
            adapter.submitList(jokes)
        }

    }

}