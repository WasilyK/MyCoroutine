package com.wasilyk.mycoroutine

import kotlinx.coroutines.flow.Flow

class DataSourceImpl : DataSource {

    override fun getData(): Flow<List<Comment>> =
        App.getDatabase().getCommentDao().selectAll()

    override fun insert(comment: Comment) =
        App.getDatabase().getCommentDao().insert(comment)

    override fun delete(comment: Comment) =
        App.getDatabase().getCommentDao().delete(comment)

    override fun selectMaxId(): Int =
        App.getDatabase().getCommentDao().selectMaxId()
}