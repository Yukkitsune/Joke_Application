package com.example.jokeapplication.data

import androidx.annotation.DrawableRes
import java.util.UUID;

data class Joke(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val question: String,
    val answer: String,
    @DrawableRes val image: Int?
)
