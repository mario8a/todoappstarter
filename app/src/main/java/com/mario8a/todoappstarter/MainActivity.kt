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
import com.mario8a.todoappstarter.presentation.home.HomeScreenRoot
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoappstarterTheme {
                HomeScreenRoot()
            }
        }
    }
}
