package com.example.android.architecture.blueprints.todoapp.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.TodoDestinations
import com.example.android.architecture.blueprints.todoapp.TodoNavigationActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* AppModalDrawer 왜 커스텀 함수로 만들었지?
* AppDrawer의 파라미터로 navigationActions를 넘겨주지 않고, 필요한 메서드만 넘겨주네
* */
@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigationActions: TodoNavigationActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                navigateToTasks = { navigationActions.navigateToTasks() },
                navigateToStatistics = { navigationActions.navigateToStatistics() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        },
    ) {
        content()
    }
}

/*
* fillMaxSize?
* 이벤트는 받아서 바로 실행만 하네
* */
@Composable
private fun AppDrawer(
    currentRoute: String,
    navigateToTasks: () -> Unit,
    navigateToStatistics: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        DrawerHeader()
        DrawerButton(
            painter = painterResource(id = R.drawable.ic_list),
            label = stringResource(id = R.string.list_title),
            isSelected = currentRoute == TodoDestinations.TASKS_ROUTE,
            action = {
                navigateToTasks()
                closeDrawer()
            }
        )
        DrawerButton(
            painter = painterResource(id = R.drawable.ic_statistics),
            label = stringResource(id = R.string.statistics_title),
            isSelected = currentRoute == TodoDestinations.STATISTICS_ROUTE,
            action = {
                navigateToStatistics()
                closeDrawer()
            }
        )
    }
}

/*
* 파라미터로 받은 modifier를 활용하지 않고 Modifier를 사용하는 경우도 있다
* fillMaxWidth?
* */
@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(primaryDarkColor)
            .height(dimensionResource(id = R.dimen.header_height))
            .padding(dimensionResource(id = R.dimen.header_padding)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_no_fill),
            contentDescription = stringResource(id = R.string.tasks_header_image_content_description),
            modifier = Modifier.width(dimensionResource(id = R.dimen.header_image_width)),
        )
        Text(
            text = stringResource(id = R.string.navigation_view_header_title),
            color = MaterialTheme.colors.surface,
        )
    }
}

/*
* painter를 파라미터로 받음, drawable resource를 Painter로 바꾼 값
* label을 파라미터로 받음
* 선택 상태(isSelected)를 파라미터로 받음
* 이벤트는 받아서 바로 실행만 하네
* */
@Composable
private fun DrawerButton(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin)),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = tintColor,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = tintColor,
            )
        }
    }
}