package com.example.jokeapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jokeapplication.data.CachedJokeEntity
import com.example.jokeapplication.data.UserJokeEntity

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedJokes(jokes: List<CachedJokeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedJoke(joke: CachedJokeEntity)

    @Query("SELECT * FROM cached_jokes WHERE timestamp >= :validTime ORDER BY timestamp DESC")
    suspend fun getCachedJokes(validTime: Long): List<CachedJokeEntity>

    @Query("DELETE FROM cached_jokes WHERE timestamp < :validTime")
    suspend fun deleteOldCachedJokes(validTime: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserJoke(joke: UserJokeEntity)

    @Query("SELECT * FROM user_jokes ORDER BY timestamp DESC")
    suspend fun getAllUserJokes(): List<UserJokeEntity>
}