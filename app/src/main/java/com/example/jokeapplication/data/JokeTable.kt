package com.example.jokeapplication.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity(tableName = "user_jokes")
data class UserJokeEntity(
    @SerialName("id")
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @SerialName("category")
    val category: String,
    @SerialName("question")
    val question: String,
    @SerialName("answer")
    val answer: String,
    @SerialName("source")
    val source: String = "User",
    @SerialName("image")
    @DrawableRes val image: Int?,
    @SerialName("timestamp")
    val timestamp: Long = System.currentTimeMillis()
)

@Serializable
@Entity(tableName = "cached_jokes")
data class CachedJokeEntity(
    @SerialName("id")
    @PrimaryKey
    val id: Int,
    @SerialName("category")
    val category: String,
    @SerialName("question")
    val question: String,
    @SerialName("answer")
    val answer: String,
    @SerialName("source")
    val source: String = "Internet",
    @SerialName("image")
    @DrawableRes val image: Int?,
    @SerialName("timestamp")
    val timestamp: Long = System.currentTimeMillis()
)