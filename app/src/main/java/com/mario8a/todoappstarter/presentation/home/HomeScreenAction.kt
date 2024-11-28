package com.mario8a.todoappstarter.presentation.home

import com.mario8a.todoappstarter.domain.Task

sealed interface HomeScreenAction {
    data class onToggleTask(val task: Task):HomeScreenAction //Marcar tarea como completada
    data class onDeleteTask(val task: Task):HomeScreenAction // Borrar una tarea
    data object onDeleteAllTask:HomeScreenAction // Borrar todas las tareas
    data object onAddTask:HomeScreenAction // Navegar a la siguiente pantalla para crear una nueva tarea
}