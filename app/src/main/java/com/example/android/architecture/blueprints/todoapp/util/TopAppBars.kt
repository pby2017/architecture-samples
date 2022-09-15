package com.example.android.architecture.blueprints.todoapp.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.android.architecture.blueprints.todoapp.R

@Composable
fun TasksTopAppBar(
    openDrawer: () -> Unit,
    onFilterAllTasks: () -> Unit,
    onFilterActiveTasks: () -> Unit,
    onFilterCompletedTasks: () -> Unit,
    onClearCompletedTasks: () -> Unit,
    onRefresh: () -> Unit,
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
            FilterTasksMenu(onFilterAllTasks, onFilterActiveTasks, onFilterCompletedTasks)
            MoreTasksMenu(onClearCompletedTasks, onRefresh)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FilterTasksMenu(
    onFilterAllTasks: () -> Unit,
    onFilterActiveTasks: () -> Unit,
    onFilterCompletedTasks: () -> Unit,
) {
    TopAppBarDropdownMenu(
        iconContent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter_list),
                contentDescription = stringResource(id = R.string.menu_filter),
            )
        }
    ) { closeMenu ->
        DropdownMenuItem(onClick = { onFilterAllTasks(); closeMenu() }) {
            Text(text = stringResource(id = R.string.nav_all))
        }
        DropdownMenuItem(onClick = { onFilterActiveTasks(); closeMenu() }) {
            Text(text = stringResource(id = R.string.nav_active))
        }
        DropdownMenuItem(onClick = { onFilterCompletedTasks(); closeMenu() }) {
            Text(text = stringResource(id = R.string.nav_completed))
        }
    }
}

@Composable
private fun MoreTasksMenu(
    onClearCompletedTasks: () -> Unit,
    onRefresh: () -> Unit,
) {
    TopAppBarDropdownMenu(iconContent = {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.menu_more)
        )
    }) { closeMenu ->
        DropdownMenuItem(onClick = { onClearCompletedTasks(); closeMenu() }) {
            Text(text = stringResource(id = R.string.menu_clear))
        }
        DropdownMenuItem(onClick = { onRefresh(); closeMenu() }) {
            Text(text = stringResource(id = R.string.refresh))
        }
    }
}

/*
* @Composable ColumnScope.(() -> Unit) -> Unit?
* by remember { mutableStateOf(false) }?
* Modifier.wrapContentSize(Alignment.TopEnd)?
* Box? 여러 위젯을 겹쳐 놓을 수 있는 layout. FrameLayout과 비슷함
* Box가 FrameLayout과 비슷한데, DropdownMenu가 IconButton과 겹치치 않고 아래에 표시되는 이유는?
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