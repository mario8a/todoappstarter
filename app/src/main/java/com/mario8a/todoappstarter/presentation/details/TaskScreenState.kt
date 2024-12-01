package com.mario8a.todoappstarter.presentation.details

import androidx.compose.foundation.text.input.TextFieldState
import com.mario8a.todoappstarter.domain.Category

data class TaskScreenState(
    val taskName:TextFieldState = TextFieldState(),
    val taskDescription: TextFieldState = TextFieldState(),
//    var taskName:String = "",
//    var taskDescription: String? = null,
    val isTaskDone: Boolean = false,
    val category: Category? = null,
    val canSaveTask: Boolean = false
)