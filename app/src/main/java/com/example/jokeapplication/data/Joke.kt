package com.example.jokeapplication.data

import androidx.annotation.DrawableRes
import com.example.jokeapplication.R
import java.util.UUID;

data class Joke(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val question: String,
    val answer: String,
    val source: String,
    @DrawableRes val image: Int?
) {
    fun getCategoryColor(): Int {
        return if (source == "Internet") R.color.color_internet_source else R.color.color_user_source
    }
}
