package com.amarinag.randomuser.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomTopAppBar(@StringRes titleId: Int, onSearchClick: () -> Unit) {
    TopAppBar(title = { Text(text = stringResource(id = titleId)) }, actions = {
        IconButton(onClick = onSearchClick) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomLargeTopAppBar(
    title: String,
    imageId: String,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    LargeTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = imageId,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(if (scrollBehavior.state.collapsedFraction < .5F) 64.dp else 32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Outlined.Edit, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior
    )
}