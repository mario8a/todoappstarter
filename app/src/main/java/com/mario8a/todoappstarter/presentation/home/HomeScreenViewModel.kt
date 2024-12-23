package com.mario8a.todoappstarter.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mario8a.todoappstarter.TodoApplication
import com.mario8a.todoappstarter.data.FakeTaskLocalDataSource
import com.mario8a.todoappstarter.domain.TaskLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor (
    savedStateHandle: SavedStateHandle,
    private val taskLocalDataSource: TaskLocalDataSource
) : ViewModel() {


    var state by mutableStateOf(HomeDataState())
        private set

    // Los channels son eventos que se envian una sola vez mientras que los estados persisten
    // Con esto aseguramosque si enviamos un evento o una navegacion o un Toast solo se envie una vez
    private val eventChannel = Channel<HomeScreenEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state = state.copy(
            date = LocalDate.now().let {
                DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy").format(it)
            }
        )
        taskLocalDataSource.taskFlow
            .onEach {
                val completedTask =
                    it.filter { task -> task.isCompleted }.sortedByDescending { task -> task.date }
                val pendingTask =
                    it.filter { task -> !task.isCompleted }
                        .sortedByDescending { task ->
                            task.date
                        }

                state = state.copy(
                    summary = pendingTask.size.toString(),
                    completedTask = completedTask,
                    pendingTask = pendingTask
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: HomeScreenAction) {
        viewModelScope.launch {
            when (action) {
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