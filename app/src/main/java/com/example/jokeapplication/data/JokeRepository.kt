package com.example.jokeapplication.data

import com.example.jokeapplication.data.db.JokeDao

class JokeRepository(private val dao: JokeDao) {
    suspend fun addUserJoke(joke: UserJokeEntity) = dao.insertUserJoke(joke)
    suspend fun getUserJokes() = dao.getAllUserJokes()

    suspend fun saveCachedJokes(jokes: List<CachedJokeEntity>) = dao.insertCachedJokes(jokes)
    suspend fun getCachedJokes(validTime: Long) = dao.getCachedJokes(validTime)
    suspend fun deleteOldCachedJokes(validTime: Long) = dao.deleteOldCachedJokes(validTime)
}