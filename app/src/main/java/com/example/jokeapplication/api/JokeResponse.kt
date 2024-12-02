package com.example.jokeapplication.api

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<JokeDetail>
)

@Serializable
data class JokeDetail(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    val flags: Flags,
    val safe: Boolean,
    val id: Int,
    val lang: String
)

@Serializable
data class Flags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)