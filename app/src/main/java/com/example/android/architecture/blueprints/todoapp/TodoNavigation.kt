package com.example.android.architecture.blueprints.todoapp

import androidx.navigation.NavHostController
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TASK_ID_ARG
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TITLE_ARG
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.USER_MESSAGE_ARG
import com.example.android.architecture.blueprints.todoapp.TodoScreens.ADD_EDIT_TASK_SCREEN
import com.example.android.architecture.blueprints.todoapp.TodoScreens.STATISTICS_SCREEN
import com.example.android.architecture.blueprints.todoapp.TodoScreens.TASKS_SCREEN
import com.example.android.architecture.blueprints.todoapp.TodoScreens.TASK_DETAIL_SCREEN

/**
 * Screens used in [TodoDestinations]
 */
private object TodoScreens {
    const val TASKS_SCREEN = "tasks"
    const val STATISTICS_SCREEN = "statistics"
    const val TASK_DETAIL_SCREEN = "task"
    const val ADD_EDIT_TASK_SCREEN = "addEditTask"
}

/**
 * Arguments used in [TodoDestinations] routes
 */
object TodoDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val TASK_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

/**
 * Destinations used in the [TasksActivity]
 */
object TodoDestinations {
    const val TASKS_ROUTE = "$TASKS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val STATISTICS_ROUTE = STATISTICS_SCREEN
    const val TASK_DETAIL_ROUTE = "$TASK_DETAIL_SCREEN/{$TASK_ID_ARG}"
    const val ADD_EDIT_TASK_ROUTE = "$ADD_EDIT_TASK_SCREEN/{$TITLE_ARG}?$TASK_ID_ARG={$TASK_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class TodoNavigationActions(private val navController: NavHostController) {
    fun navigateToTasks(userMessage: Int = 0) {
        // TODO
    }

    fun navigateToStatistics() {
        // TODO
    }

    fun navigateToTaskDetail(taskId: String) {
        // TODO
    }

    fun navigateToAddEditTask(title: Int, taskId: String?) {
        // TODO
    }
}