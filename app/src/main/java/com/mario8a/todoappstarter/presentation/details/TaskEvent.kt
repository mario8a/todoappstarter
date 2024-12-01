package com.mario8a.todoappstarter.presentation.details

sealed class TaskEvent {
    data object TaskCreated: TaskEvent()
}