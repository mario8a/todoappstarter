package com.mario8a.todoappstarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mario8a.todoappstarter.presentation.home.HomeScreenRoot
import com.mario8a.todoappstarter.presentation.navigation.NavigationRoot
import com.mario8a.todoappstarter.presentation.navigation.TaskScreenDes
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoappstarterTheme {
                val navController = rememberNavController()
                NavigationRoot(navController = navController)
            }
        }
    }
}
