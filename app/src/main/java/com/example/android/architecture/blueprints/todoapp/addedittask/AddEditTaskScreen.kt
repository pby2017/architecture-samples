package com.example.android.architecture.blueprints.todoapp.addedittask

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android.architecture.blueprints.todoapp.R

/*
* rememberScaffoldState 의 역할은?
* */
@Composable
fun AddEditTaskScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { /* TODO: AddEditTaskTopAppBar 호출 */ },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::saveTask) {
                Icon(Icons.Filled.Done, stringResource(id = R.string.cd_save_task))
            }
        },
    ) {
        // TODO: 화면 구성 추가
    }
}
