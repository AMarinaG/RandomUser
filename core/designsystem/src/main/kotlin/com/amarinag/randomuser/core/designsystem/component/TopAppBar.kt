package com.amarinag.randomuser.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.amarinag.randomuser.core.designsystem.theme.spacing
import com.amarinag.randomuser.core.designsytem.R.string

const val SEARCH_ICON_TEST_TAG="search_icon_test_tag"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomTopAppBar(
    @StringRes titleId: Int, onSearchClick: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    isSearchActive: Boolean,
    onActiveChange: (Boolean) -> Unit
) {
    TopAppBar(title = { Text(text = stringResource(id = titleId)) }, actions = {
        IconButton(onClick = onSearchClick, modifier = Modifier.testTag(SEARCH_ICON_TEST_TAG)) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    })
    AnimatedVisibility(visible = isSearchActive) {
        DockedSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = isSearchActive,
            onActiveChange = onActiveChange,
            placeholder = { Text(text = stringResource(string.core_designsystem_search)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.hugest),
            trailingIcon = {
                IconButton(modifier = Modifier, onClick = {
                    if (query.isEmpty()) {
                        onActiveChange(false)
                    }
                    onQueryChange("")
                }) {
                    Icon(imageVector = Icons.Outlined.Clear, contentDescription = null)
                }
            }
        ) {
        }
    }
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
                        .size(if (scrollBehavior.state.collapsedFraction < .5F) MaterialTheme.spacing.largest * 2 else MaterialTheme.spacing.largest)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Text(text = title)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior
    )
}