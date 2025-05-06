package com.mario8a.todoappstarter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.google.common.truth.Truth
import com.mario8a.todoappstarter.data.TaskDao
import com.mario8a.todoappstarter.data.TaskEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var taskDao: TaskDao

    @Before
    fun init() {
        hiltRule.inject()

        runBlocking {
            taskDao.deleteAllTasks()
        }
    }

    @Test
    fun whenTask_showEmptyState() {
        composeTestRule.onNodeWithContentDescription("Empty Task State")
            .assertIsDisplayed()
    }

    @Test
    fun whenTaskExist_showTaskList() {
        runBlocking {
            val testtask = TaskEntity(
                id = "1",
                title = "Test Task",
                description = "Test Descroption",
                isCompleted = false,
                category = null,
                date = System.currentTimeMillis()
            )
            taskDao.upsertTask(testtask)
        }

        composeTestRule.onNodeWithContentDescription("Pending Task: Test Task")
            .assertIsDisplayed()
    }

    @Test
    fun whenCreatingNewtask_taskAppearsInList() {
        composeTestRule.onNodeWithContentDescription("Add New Task Button")
            .performClick()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithContentDescription("Task Screen").isDisplayed()
        }

        composeTestRule.onNodeWithContentDescription("Task Title Input")
            .performTextInput("New Test Task")
        composeTestRule.onNodeWithContentDescription("Task Description Input")
            .performTextInput("New Test Description")

        composeTestRule.onNodeWithContentDescription("Save Task Button")
            .performClick()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithContentDescription("Home Screen").isDisplayed()
        }

        composeTestRule.onNodeWithContentDescription("New Test Task").isDisplayed()

        runBlocking {
            val task = taskDao.getAllTasks().first()
            Truth.assertThat(task.any() {it.title == "New Test Task"}).isTrue()
        }
    }
}