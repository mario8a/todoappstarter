package com.mario8a.todoappstarter

import android.app.Application
import com.mario8a.todoappstarter.data.DataSourceFactory
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TodoApplication: Application() {
    val dispatchersIO: CoroutineDispatcher
        get() = Dispatchers .IO

    val dataSource: TaskLocalDataSource
        get() = DataSourceFactory.createDataSource(this, dispatchersIO)
}