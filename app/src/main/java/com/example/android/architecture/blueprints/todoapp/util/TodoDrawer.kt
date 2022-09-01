package com.example.android.architecture.blueprints.todoapp.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope

/*
* AppModalDrawer 왜 커스텀 함수로 만들었지?
* */
@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(

            )
        },
    ) {

    }
}

/*
* fillMaxSize?
* */
@Composable
private fun AppDrawer(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        DrawerHeader()
        DrawerButton()
        DrawerButton()
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier,
) {

}

/*
* painter를 파라미터로 받음, drawable resource를 Painter로 바꾼 값
* label을 파라미터로 받음
* 선택 상태(isSelected)를 파라미터로 받음
* */
@Composable
private fun DrawerButton() {

}