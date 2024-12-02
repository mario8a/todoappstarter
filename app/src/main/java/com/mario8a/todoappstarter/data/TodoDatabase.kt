package com.mario8a.todoappstarter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDato(): TaskDao
}