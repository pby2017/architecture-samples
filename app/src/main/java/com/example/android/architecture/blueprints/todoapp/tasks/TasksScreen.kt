package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.LoadingContent
import com.example.android.architecture.blueprints.todoapp.util.TasksTopAppBar

/*
* fillMaxSize?
* FloatingActionButton의 위치 이동 가능할까?
* hiltViewModel?
* collectAsStateWithLifecycle?
* 외부에서 TasksContent 로 패딩 넣는 방법
* */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TasksScreen(
    onAddTask: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TasksTopAppBar(
                openDrawer = openDrawer,
                onFilterAllTasks = { viewModel.setFiltering(TasksFilterType.ALL_TASKS) },
                onFilterActiveTasks = { viewModel.setFiltering(TasksFilterType.ACTIVE_TASKS) },
                onFilterCompletedTasks = { viewModel.setFiltering(TasksFilterType.COMPLETED_TASKS) },
                onClearCompletedTasks = { viewModel.clearCompletedTasks() },
                onRefresh = { viewModel.refresh() }
            )
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
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        TasksContent(
            loading = uiState.isLoading,
            currentFilteringLabel = uiState.filteringUiInfo.currentFilteringLabel,
            onRefresh = viewModel::refresh,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun TasksContent(
    loading: Boolean,
    @StringRes currentFilteringLabel: Int,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoadingContent(
        loading = loading,
        onRefresh = onRefresh,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
        ) {
            Text(
                text = stringResource(id = currentFilteringLabel),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
                    vertical = dimensionResource(id = R.dimen.vertical_margin),
                ),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}