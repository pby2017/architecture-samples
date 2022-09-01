package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.android.architecture.blueprints.todoapp.R

/*
* fillMaxSize?
* FloatingActionButton의 위치 이동 가능할까?
* */
@Composable
fun TasksScreen(
    onAddTask: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // TasksTopAppBar()
        },
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTask) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_task)
                )
            }
        },
    ) {
        // TODO
    }
}