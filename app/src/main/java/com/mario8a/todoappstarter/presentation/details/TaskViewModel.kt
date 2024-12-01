package com.mario8a.todoappstarter.presentation.details

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mario8a.todoappstarter.data.FakeTaskLocalDataSource
import com.mario8a.todoappstarter.domain.Task
import com.mario8a.todoappstarter.presentation.navigation.TaskScreenDes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID


class TaskViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val fakeTaskLocalDataSource = FakeTaskLocalDataSource

    val taskData = savedStateHandle.toRoute<TaskScreenDes>()

    var state by mutableStateOf(TaskScreenState())
        private set

    private val eventsChannel = Channel<TaskEvent>()
    val events = eventsChannel.receiveAsFlow()
    private val canSaveTask = snapshotFlow { state.taskName.text.toString() }

    private var editedTask: Task? = null

    init {

        taskData.taskId?.let {
            viewModelScope.launch {
                fakeTaskLocalDataSource.getTaskById(taskData.taskId)?.let { task ->
                    editedTask= task
                    state = state.copy(
                        taskName = TextFieldState(task.title),
                        taskDescription = TextFieldState(task.description?:""),
                        isTaskDone = task.isCompleted,
                        category = task.category
                    )
                }
            }
        }

        canSaveTask.onEach {
            state = state.copy(canSaveTask = it.isNotEmpty())
        }.launchIn(viewModelScope)
    }

    fun onAction(actionTask: ActionTask) {
        viewModelScope.launch {
            when (actionTask) {
                is ActionTask.ChangeTaskCategory -> {
                    state = state.copy(
                        category = actionTask.category
                    )
                }
                is ActionTask.ChangeTaskDone -> {
                    state = state.copy(
                        isTaskDone = actionTask.isTaskDone
                    )
                }

                ActionTask.SaveTask -> {
                    editedTask?.let {
                        fakeTaskLocalDataSource.updateTask(
                            updatedTask = it.copy(
                                id = it.id,
                                title = state.taskName.text.toString(),
                                description = state.taskDescription.text.toString(),
                                isCompleted = state.isTaskDone,
                                category = state.category
                            )
                        )
                    }?:run {
                        val task= Task(
                            id =UUID.randomUUID().toString(),
                            title = state.taskName.text.toString(),
                            description = state.taskDescription.text.toString(),
                            isCompleted = state.isTaskDone,
                            category = state.category
                        )
                        fakeTaskLocalDataSource.addTask(
                            task = task
                        )
                    }
                    eventsChannel.send(TaskEvent.TaskCreated)
                }

                else -> Unit
            }
        }

    }
}