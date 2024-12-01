package com.example.jokeapplication.ui.recycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.ItemJokeBinding
import com.example.jokeapplication.ui.recycler.util.JokeDiffUtilCallback

class JokeAdapter(private val onClick: (Joke) -> Unit) :
    ListAdapter<Joke, JokeAdapter.JokeViewHolder>(JokeDiffUtilCallback()) {

    inner class JokeViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke) {
            binding.tvCategory.text = joke.category
            binding.tvQuestion.text = joke.question
            binding.tvAnswer.text = joke.answer
            binding.tvCategory.setTextColor(
                ContextCompat.getColor(binding.root.context, joke.getCategoryColor())
            )
            joke.image?.let {
                binding.ivJokeImage.setImageResource(it)
            } ?: run {
                binding.ivJokeImage.visibility = View.GONE
            }
            itemView.setOnClickListener {
                onClick(joke)
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
