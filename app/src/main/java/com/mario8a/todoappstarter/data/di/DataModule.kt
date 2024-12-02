package com.mario8a.todoappstarter.data.di

import android.content.Context
import androidx.room.Room
import com.mario8a.todoappstarter.data.RoomTaskLocalDataSource
import com.mario8a.todoappstarter.data.TaskDao
import com.mario8a.todoappstarter.data.TodoDatabase
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext
        context: Context,
    ): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(
        database: TodoDatabase
    ): TaskDao = database.taskDato()

    @Provides
    @Singleton
    fun provideTaskLocalDataSource(
        taskDao: TaskDao,
        dispatcher: CoroutineDispatcher
    ): TaskLocalDataSource = RoomTaskLocalDataSource(taskDao)
}