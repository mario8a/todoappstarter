package com.mario8a.todoappstarter.presentation.details

import com.mario8a.todoappstarter.domain.Category

sealed interface ActionTask {
    data object SaveTask: ActionTask
    data object Back: ActionTask
    data class ChangeTaskCategory(val category: Category?): ActionTask
    data class ChangeTaskDone(val isTaskDone: Boolean): ActionTask
}