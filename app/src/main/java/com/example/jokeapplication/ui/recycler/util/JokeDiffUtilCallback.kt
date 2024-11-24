package com.example.jokeapplication.ui.recycler.util

import androidx.recyclerview.widget.DiffUtil
import com.example.jokeapplication.data.Joke

class JokeDiffUtilCallback : DiffUtil.ItemCallback<Joke>() {
    override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.question == newItem.question
    }

    override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem == newItem
    }
}
