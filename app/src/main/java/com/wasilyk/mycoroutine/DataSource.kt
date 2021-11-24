package com.wasilyk.mycoroutine

import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun getData(): Flow<List<Comment>>
    fun insert(comment: Comment)
    fun delete(comment: Comment)
    fun selectMaxId(): Int
}