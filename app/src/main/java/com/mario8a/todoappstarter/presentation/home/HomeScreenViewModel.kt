package com.mario8a.todoappstarter.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario8a.todoappstarter.data.FakeTaskLocalDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val taskLocalDataSource = FakeTaskLocalDataSource

    var state by mutableStateOf(HomeDataState())
        private set

    // Los channels son eventos que se envian una sola vez mientras que los estados persisten
    // Con esto aseguramosque si enviamos un evento o una navegacion o un Toast solo se envie una vez
    private val eventChannel = Channel<HomeScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        taskLocalDataSource.taskFlow
            .onEach {
                val completedTask = it.filter { task -> task.isCompleted }
                val pendingTask = it.filter { task -> !task.isCompleted }

                state = state.copy(
                    date = "March 9, 2024",
                    summary = "${pendingTask.size} incompleted, ${completedTask.size} completed",
                    completedTask = completedTask,
                    pendingTask = pendingTask
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: HomeScreenAction) {
        viewModelScope.launch {
            when(action){
                HomeScreenAction.onDeleteAllTask -> {
                    taskLocalDataSource.deleteAllTask()
                    eventChannel.send(HomeScreenEvent.DeletedAllTasks)
                }
                is HomeScreenAction.onDeleteTask -> {
                    taskLocalDataSource.removeTask(action.task)
                    eventChannel.send(HomeScreenEvent.DeletedTask)
                }
                is HomeScreenAction.onToggleTask -> {
                    val updatedTask = action.task.copy(isCompleted = !action.task.isCompleted)
                    taskLocalDataSource.updateTask(updatedTask)
                }
                else -> Unit
            }
        }
    }
}