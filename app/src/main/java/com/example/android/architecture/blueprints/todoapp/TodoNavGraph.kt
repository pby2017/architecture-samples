package com.example.android.architecture.blueprints.todoapp

import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TASK_ID_ARG
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TITLE_ARG
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.USER_MESSAGE_ARG
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksScreen
import com.example.android.architecture.blueprints.todoapp.util.AppModalDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* TodoNavGraph?
* NavHostController?
* rememberNavController?
* NavHost?
* composable?
* NavBackStackEntry?
* remember(navController) calculation 안 코드를 그냥 안쓰고 remember로 감싸는 이유는?
* openDrawer를 coroutineScope.launch 로 실행하는 이유는?
* entry는 argument 전달하는 역할도 함
* */
@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = TodoDestinations.TASKS_ROUTE,
    navActions: TodoNavigationActions = remember(navController) {
        TodoNavigationActions(navController = navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = TodoDestinations.TASKS_ROUTE,
            arguments = listOf(
                navArgument(name = USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            ),
        ) { entry ->
            AppModalDrawer(
                drawerState = drawerState,
                currentRoute = currentRoute,
                navigationActions = navActions
            ) {
                TasksScreen(
                    onAddTask = { navActions.navigateToAddEditTask(R.string.add_task, null) },
                    onTaskClick = { task -> navActions.navigateToTaskDetail(task.id) },
                    openDrawer = { coroutineScope.launch { drawerState.open() } },
                )
            }
        }
        composable(
            route = TodoDestinations.ADD_EDIT_TASK_ROUTE,
            arguments = listOf(
                navArgument(name = TITLE_ARG) { type = NavType.IntType },
                navArgument(name = TASK_ID_ARG) { type = NavType.StringType;nullable = true },
            ),
        ) { entry ->
            val taskId = entry.arguments?.getString(TASK_ID_ARG)
            AddEditTaskScreen()
        }
    }
}