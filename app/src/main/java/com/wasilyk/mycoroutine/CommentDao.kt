package com.wasilyk.mycoroutine

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: Comment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: List<Comment>)

    @Query("SELECT * FROM comments")
    fun selectAll(): Flow<List<Comment>>

    @Query("SELECT MAX(id) FROM comments")
    fun selectMaxId(): Int

    @Delete
    fun delete(comment: Comment)

    @Delete
    fun clear(comments: List<Comment>)
}