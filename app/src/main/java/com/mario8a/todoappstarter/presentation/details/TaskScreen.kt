package com.mario8a.todoappstarter.presentation.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mario8a.todoappstarter.R
import com.mario8a.todoappstarter.domain.Category
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme

@Composable
fun TaskScreenRoot() {
    val viewModel = viewModel<TaskViewModel>()
    val state = viewModel.state
    val event = viewModel.events

    val context = LocalContext.current

    LaunchedEffect(true) {
        event.collect { event ->
            when (event) {
                TaskEvent.TaskCreated -> {
                    Toast.makeText(
                        context, context.getString(R.string.task_saved), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    TaskScreen(
        state = state, onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier, state: TaskScreenState, onAction: (ActionTask) -> Unit
) {

    var isDescriptionFocus by remember {
        mutableStateOf(false)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = stringResource(R.string.task)
                )
            },
            navigationIcon = {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable {
                        onAction(
                            ActionTask.Back
                        )
                    })
            },
        )
    }) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(
                8.dp
            ), modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.done),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                Checkbox(
                    checked = state.isTaskDone,
                    onCheckedChange = {
                        onAction(
                            ActionTask.ChangeTaskDone(
                                isTaskDone = it
                            )
                        )
                    },
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                    isExpanded = true
                }) {

                    Text(
                        text = state.category?.toString() ?: stringResource(R.string.category),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                    Box(
                        modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Add Task",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                        DropdownMenu(modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest
                        ), expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                            Column {
                                Category.entries.forEach { category ->
                                    Text(text = category.name,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.onSurface
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .padding(
                                                8.dp
                                            )
                                            .clickable {
                                                onAction(
                                                    ActionTask.ChangeTaskCategory(
                                                        category = category
                                                    )
                                                )
                                            })
                                }
                            }
                        }
                    }
                }


            }

            BasicTextField(
                value = state.taskName,
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                decorationBox = { innerBox ->
                    Column {
                        if (state.taskName.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.task_name),
                            )
                        } else {
                            innerBox()
                        }
                    }
                },
            )
            BasicTextField(
                value = state.taskDescription ?: "",
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                maxLines = 15,
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onFocusChanged {
                        isDescriptionFocus = it.isFocused
                    },
                decorationBox = { innerBox ->
                    Column {
                        if (state.taskDescription.isNullOrEmpty() && !isDescriptionFocus) {
                            Text(
                                text = stringResource(R.string.task_description),
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            )
                        } else {
                            innerBox()
                        }
                    }
                },
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Button(
                enabled = state.canSaveTask, onClick = {
                    onAction(
                        ActionTask.SaveTask
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}

@Composable
@Preview()
fun TaskScreenLightPreview(
    @PreviewParameter(TaskScreenStatePreviewProvider::class) state: TaskScreenState
) {
    TodoappstarterTheme {
        TaskScreen(state = state, onAction = {})
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
fun TaskScreenDarkPreview(
    @PreviewParameter(TaskScreenStatePreviewProvider::class) state: TaskScreenState
) {
    TodoappstarterTheme {
        TaskScreen(state = state, onAction = {})
    }
}
