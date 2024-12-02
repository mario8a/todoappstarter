package com.mario8a.todoappstarter.data

import android.content.Context
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher

object DataSourceFactory {
    fun createDataSource(
        context: Context,
        dispatcher: CoroutineDispatcher
    ): TaskLocalDataSource {
        val database = TodoDatabase.getDatabase(context)
        return RoomTaskLocalDataSource(database.taskDato(), dispatcher)
    }
}