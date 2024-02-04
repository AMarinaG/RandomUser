package com.amarinag.randomuser.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomTopAppBar(@StringRes titleId: Int, onSearchClick: () -> Unit) {
    TopAppBar(title = { Text(text = stringResource(id = titleId)) }, actions = {
        IconButton(onClick = onSearchClick) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    })
}