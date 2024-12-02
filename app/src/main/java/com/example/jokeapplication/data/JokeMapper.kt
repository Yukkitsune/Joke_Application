package com.example.jokeapplication.data

import com.example.jokeapplication.R

fun UserJokeEntity.toJoke(): Joke {
    return Joke(
        id = id,
        category = category,
        question = question,
        answer = answer,
        source = source,
        image = image
    )
}

fun CachedJokeEntity.toJoke(): Joke {
    return Joke(
        id = id.toString(),
        category = category,
        question = question,
        answer = answer,
        source = source,
        image = image,
    )
}

fun Joke.toUserJokeEntity(): UserJokeEntity {
    return UserJokeEntity(
        id = id,
        category = category,
        question = question,
        answer = answer,
        source = source,
        image = image,
        timestamp = System.currentTimeMillis()
    )
}

fun Joke.toCachedJokeEntity(): CachedJokeEntity {
    return CachedJokeEntity(
        id = this.hashCode(),
        category = this.category,
        question = this.question,
        answer = this.answer,
        source = this.source,
        image = this.image ?: R.drawable.question_sign,
        timestamp = System.currentTimeMillis()
    )
}

