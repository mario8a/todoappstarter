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

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        // Se crea Singleton
        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }


}