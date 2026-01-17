package com.piyushhood.tmdbrowser.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piyushhood.tmdbrowser.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getMovies() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getMoviesPaging() : PagingSource<Int , MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    fun getMovieById(movieId : Int) : Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetMovies(movies : List<MovieEntity>)

    @Query("DELETE From movies")
    suspend fun clearMovies()
}