package com.pmacademy.razviipt21.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserPost::class],
    version = 1
)

abstract class UserPostsDatabase : RoomDatabase() {
    abstract fun getUserPostDao(): UserPostDao

    companion object {
        @Volatile
        private var instance: UserPostsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            UserPostsDatabase::class.java, "user_post_list.db"
        ).build()
    }
}
