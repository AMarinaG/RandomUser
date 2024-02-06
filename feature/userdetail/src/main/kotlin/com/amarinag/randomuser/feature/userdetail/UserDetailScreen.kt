package com.amarinag.randomuser.feature.userdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amarinag.randomuser.core.designsystem.component.IconTwoLinesItem
import com.amarinag.randomuser.core.designsystem.component.RandomLargeTopAppBar
import com.amarinag.randomuser.core.designsystem.theme.spacing
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.model.UserCoordinates
import com.amarinag.randomuser.core.model.UserRegistered
import com.amarinag.randomuser.feature.userdetail.R.string
import com.amarinag.randomuser.feature.userdetail.UserDetailUiState.Error
import com.amarinag.randomuser.feature.userdetail.UserDetailUiState.Loading
import com.amarinag.randomuser.feature.userdetail.UserDetailUiState.Success
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun UserDetailRoute(
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UserDetailScreen(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    uiState: UserDetailUiState,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    when (uiState) {
        Error -> Text(text = stringResource(id = string.feature_userdetail_error))
        Loading -> Text(text = stringResource(id = string.feature_userdetail_loading))
        is Success -> Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                RandomLargeTopAppBar(
                    title = uiState.user.name.fullname,
                    imageId = uiState.user.picture.medium,
                    scrollBehavior = scrollBehavior
                )
            }) { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(MaterialTheme.spacing.normal)
                    .verticalScroll(rememberScrollState())
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)),

                ) {
                UserDetail(user = uiState.user)

            }
        }

    }
}

@Composable
fun UserDetail(user: User) {
    IconTwoLinesItem(
        title = stringResource(R.string.feature_userdetail_fullname),
        subtitle = user.name.fullname,
        icon = Icons.Outlined.AccountCircle
    )
    Divider()
    IconTwoLinesItem(
        title = stringResource(R.string.feature_userdetail_email),
        subtitle = user.email,
        icon = Icons.Outlined.Email
    )
    Divider()
    IconTwoLinesItem(
        title = stringResource(R.string.feature_userdetail_gender),
        subtitle = user.gender,
        icon = Icons.Outlined.Face
    )
    Divider()
    IconTwoLinesItem(
        title = stringResource(R.string.feature_userdetail_registered_date),
        subtitle = user.registered.toHumanDate(),
        icon = Icons.Outlined.DateRange
    )
    Divider()
    IconTwoLinesItem(
        title = stringResource(R.string.feature_userdetail_phone),
        subtitle = user.phone,
        icon = Icons.Outlined.Phone
    )
    Divider()
    Column {
        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.largest * 2,
                top = MaterialTheme.spacing.normal
            ),
            text = stringResource(id = string.feature_userdetail_address),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        val location = user.location.coordinates.latlong()
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 10f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.largest * 2,
                    top = MaterialTheme.spacing.normal
                )
                .height(MaterialTheme.spacing.mapHeight),
            cameraPositionState = cameraPositionState

        ) {
            Marker(state = MarkerState(location))
        }
    }
}

private fun UserCoordinates.latlong() = LatLng(this.latitude.toDouble(), longitude.toDouble())
private fun UserRegistered.toHumanDate() =
    date.toInstant().toLocalDateTime(TimeZone.UTC).date.toString()