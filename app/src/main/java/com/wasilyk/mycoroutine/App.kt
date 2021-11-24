package com.wasilyk.mycoroutine

import android.app.Application
import androidx.room.Room

class App : Application() {

    companion object {
        private var instance: App? = null
        const val DB_NAME = "comments_db"
        private var db: CommentDatabase? = null

        fun getDatabase(): CommentDatabase {
            if(instance == null) {
                throw Exception("Application can't be null")
            }
            synchronized(DB_NAME) {
                if(db == null) {
                    db = Room.databaseBuilder(
                        instance!!.applicationContext,
                        CommentDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return db!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}