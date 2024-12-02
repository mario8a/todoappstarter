package com.mario8a.todoappstarter

import android.app.Application
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@HiltAndroidApp
class TodoApplication: Application()