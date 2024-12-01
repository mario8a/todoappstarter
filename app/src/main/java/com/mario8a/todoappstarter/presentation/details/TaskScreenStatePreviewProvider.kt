package com.mario8a.todoappstarter.presentation.details

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mario8a.todoappstarter.domain.Category

class  TaskScreenStatePreviewProvider:PreviewParameterProvider<TaskScreenState> {
    override val values: Sequence<TaskScreenState>
        get() = sequenceOf(
            TaskScreenState(
                taskName = TextFieldState("Task 1"),
                taskDescription = TextFieldState("Description 1"),
//                taskName = "Task 1",
//                taskDescription = "Description 1",
                isTaskDone = false,
                category = Category.WORK
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 2"),
                taskDescription = TextFieldState("Description 2"),
//                taskName = "Task 2",
//                taskDescription = "Description 2",
                isTaskDone = true,
                category = Category.WORK
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 3"),
                taskDescription = TextFieldState("Description 3"),
//                taskName = "Task 3",
//                taskDescription = "Description 3",
                isTaskDone = false,
                category = Category.OTHER
            ),
        )
}