package com.mario8a.todoappstarter.presentation.home

import com.mario8a.todoappstarter.domain.Task

data class HomeDataState(
    val date: String = "",
    val summary: String = "",
    val completedTask: List<Task> = emptyList(),
    val pendingTask: List<Task> = emptyList()
)