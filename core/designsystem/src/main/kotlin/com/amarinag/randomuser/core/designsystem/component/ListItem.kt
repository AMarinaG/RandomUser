package com.amarinag.randomuser.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.amarinag.randomuser.core.designsystem.theme.spacing

@Composable
fun ImageTwoLinesItem(
    title: String,
    subtitle: String,
    imageUrl: String,
    onItemClick: (String) -> Unit,
    id: String = subtitle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(id) })
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
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = subtitle, style = MaterialTheme.typography.titleMedium)
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