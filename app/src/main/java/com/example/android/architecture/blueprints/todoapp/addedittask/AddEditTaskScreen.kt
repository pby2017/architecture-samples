package com.example.android.architecture.blueprints.todoapp.addedittask

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.AddEditTaskTopAppBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/*
* rememberScaffoldState 의 역할은?
* Modifier.padding(paddingValues = paddingValues) 의 의미는?
* */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AddEditTaskScreen(
    @StringRes topBarTitle: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            AddEditTaskTopAppBar(
                title = topBarTitle,
                onBack = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::saveTask) {
                Icon(Icons.Filled.Done, stringResource(id = R.string.cd_save_task))
            }
        },
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        AddEditTaskContent(
            loading = uiState.isLoading,
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}

@Composable
fun AddEditTaskContent(
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (loading) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = true),
            onRefresh = { /* Do Nothing */ }
        ) { /* Do Nothing */ }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.horizontal_margin))
                .verticalScroll(rememberScrollState())
        ) {
            // TODO: 정보 입력 영역 추가
        }
    }
}
