package com.amarinag.randomuser.core.designsystem.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import coil.compose.AsyncImage
import com.amarinag.randomuser.core.designsystem.theme.spacing

const val DeleteUserTestTag = "DeleteUserTestTag"

@Composable
fun ImageTwoLinesItem(
    title: String,
    subtitle: String,
    imageUrl: String,
    onItemClick: () -> Unit,
    onItemDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(MaterialTheme.spacing.normal)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(MaterialTheme.spacing.hugest + MaterialTheme.spacing.small)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onItemDelete, modifier = Modifier.testTag(DeleteUserTestTag)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = subtitle, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun ImageThreeLinesItem(
    title: String,
    subtitle: String,
    label: String,
    imageUrl: String,
    onItemClick: () -> Unit,
    onItemDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(MaterialTheme.spacing.normal)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(MaterialTheme.spacing.hugest + MaterialTheme.spacing.small)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))
        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onItemDelete, modifier = Modifier.testTag(DeleteUserTestTag)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = subtitle, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = label, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@Preview
fun ImageTwoLinesItemPlaceholder() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.normal)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(MaterialTheme.spacing.hugest + MaterialTheme.spacing.small)
                .background(MaterialTheme.colorScheme.onBackground)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .height(MaterialTheme.spacing.normal)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .height(MaterialTheme.spacing.normal)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
@Preview
fun ImageThreeLinesItemPlaceholder() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.normal)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(MaterialTheme.spacing.hugest + MaterialTheme.spacing.small)
                .background(MaterialTheme.colorScheme.onBackground)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.normal))
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .height(MaterialTheme.spacing.normal)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .height(MaterialTheme.spacing.normal)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .height(MaterialTheme.spacing.normal)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun IconTwoLinesItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconDescription: String? = null,
    id: String = subtitle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.normal)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(MaterialTheme.spacing.largest)
        )
        Spacer(
            modifier =
                Modifier.width(MaterialTheme.spacing.normal)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ), label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.primary,
            ),
            start = Offset(startOffsetX, 0F),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}