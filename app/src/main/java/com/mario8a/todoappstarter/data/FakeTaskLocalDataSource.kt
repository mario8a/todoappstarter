package com.mario8a.todoappstarter.data

import com.mario8a.todoappstarter.domain.Task
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import com.mario8a.todoappstarter.presentation.home.providers.completedTask
import com.mario8a.todoappstarter.presentation.home.providers.pendingTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object FakeTaskLocalDataSource: TaskLocalDataSource {
    // Un flow es la forma en que observo cambios dentro de esa lista como ej: un borrado, añadir
    // La vista es notificada
    private val _taskFlow = MutableStateFlow<List<Task>>(emptyList())

    init {
        _taskFlow.value = completedTask + pendingTask
    }

    override val taskFlow: Flow<List<Task>>
        get() = _taskFlow

    override suspend fun addTask(task: Task) {
        val tasks = _taskFlow.value.toMutableList()
        tasks.add(task)
        delay(100)
        _taskFlow.value = tasks
    }

    override suspend fun updateTask(updatedTask: Task) {
        val tasks = _taskFlow.value.toMutableList()
        val taskIndex = tasks.indexOfFirst { it.id == updatedTask.id }
        if (taskIndex != -1) {
            tasks[taskIndex] = updatedTask
            delay(100)
            _taskFlow.value = tasks
        }
    }

    override suspend fun removeTask(task: Task) {
        val tasks = _taskFlow.value.toMutableList()
        tasks.remove(task)
        delay(100)
        _taskFlow.value = tasks
    }

    override suspend fun deleteAllTask() {
        delay(1000L)
        _taskFlow.value = emptyList()
    }

    override suspend fun getTaskById(id: String): Task? {
        delay(1000L)
        return _taskFlow.value.firstOrNull{it.id == id}
    }

}