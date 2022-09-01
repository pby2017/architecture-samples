package com.example.android.architecture.blueprints.todoapp.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.android.architecture.blueprints.todoapp.R

@Composable
fun TasksTopAppBar(
    openDrawer: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.open_drawer),
                )
            }
        },
        actions = {
            FilterTasksMenu()
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FilterTasksMenu(

) {
    TopAppBarDropdownMenu(
        iconContent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter_list),
                contentDescription = stringResource(id = R.string.menu_filter),
            )
        }
    ) {
        // TODO
    }
}

/*
* @Composable ColumnScope.(() -> Unit) -> Unit?
* by remember { mutableStateOf(false) }?
* Modifier.wrapContentSize(Alignment.TopEnd)?
* */
@Composable
private fun TopAppBarDropdownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded }) {
            iconContent()
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopEnd),
        ) {
            content { expanded = !expanded }
        }
    }
}