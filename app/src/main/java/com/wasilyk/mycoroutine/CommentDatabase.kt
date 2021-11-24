package com.wasilyk.mycoroutine

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Comment::class], version = 1)
abstract class CommentDatabase : RoomDatabase() {

    abstract fun getCommentDao(): CommentDao
}