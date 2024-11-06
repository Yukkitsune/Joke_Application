package com.example.jokeapplication.data

import androidx.annotation.DrawableRes

data class Joke(
    val category: String,
    val question: String,
    val answer: String,
    @DrawableRes val image: Int?

)