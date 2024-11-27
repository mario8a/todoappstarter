package com.mario8a.todoappstarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mario8a.todoappstarter.data.FakeTaskLocalDataSource
import com.mario8a.todoappstarter.domain.Task
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoappstarterTheme {
                var text by remember {
                    mutableStateOf("")
                }
                val fakeLocalDataSource = FakeTaskLocalDataSource
                LaunchedEffect(true) {
                    fakeLocalDataSource.taskFlow.collect {
                        text = it.toString()
                    }
                }

                LaunchedEffect(true) {
                    fakeLocalDataSource.addTask(
                        Task(
                            id = UUID.randomUUID().toString(),
                            title = "Task 1",
                            description = "desc 1"
                        )
                    )
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(text = text, modifier = Modifier.padding(top = innerPadding.calculateTopPadding()).fillMaxSize())
                }
            }
        }
    }
}
