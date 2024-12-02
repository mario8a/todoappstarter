package com.mario8a.todoappstarter.domain

import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    val taskFlow: Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTask(updatedTask: Task)
    suspend fun removeTask(task: Task)
    suspend fun deleteAllTask()
    suspend fun getTaskById(id: String): Task?
}