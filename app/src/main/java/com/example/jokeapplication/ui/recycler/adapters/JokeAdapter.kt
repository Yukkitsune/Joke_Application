package com.example.jokeapplication.ui.recycler.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.R
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.ItemJokeBinding
import com.example.jokeapplication.ui.joke_details.JokeDetailsActivity
import com.example.jokeapplication.ui.recycler.util.JokeDiffUtilCallback

class JokeAdapter : ListAdapter<Joke, JokeAdapter.JokeViewHolder>(JokeDiffUtilCallback()) {

    inner class JokeViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke) {
            binding.tvCategory.text = joke.category
            binding.tvQuestion.text = joke.question
            binding.tvAnswer.text = joke.answer
            joke.image?.let {
                binding.iv.setImageResource(it)
            } ?: run {
                binding.iv.visibility = View.GONE
            }
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = JokeDetailsActivity.createIntent(
                    context,
                    joke.category,
                    joke.question,
                    joke.answer
                )
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ItemJokeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}