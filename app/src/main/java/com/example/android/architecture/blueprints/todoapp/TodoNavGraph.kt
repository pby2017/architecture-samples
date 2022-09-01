package com.example.android.architecture.blueprints.todoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.USER_MESSAGE_ARG

/*
* TodoNavGraph?
* NavHostController?
* rememberNavController?
* NavHost?
* composable?
* NavBackStackEntry?
* */
@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TodoDestinations.TASKS_ROUTE,
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

        }
    }
}