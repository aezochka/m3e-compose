@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package dev.aezochka.m3e

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Expressive list item: классический ListItem + пружинный press-морф
 * (16dp → 28dp под пальцем) вместо плоского ripple.
 * InteractiveListItem (с shape-морфами) в beta01 приватный —
 * поэтому морф делаем сами через [m3ePress].
 */
@Composable
fun M3EListItem(
    headline: String,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
) {
    ListItem(
        headlineContent = { Text(headline) },
        modifier = modifier.m3ePress(
            onClick = onClick,
            corner = 16.dp,
            cornerPressed = 28.dp,
        ),
        overlineContent = overline?.let { { Text(it) } },
        supportingContent = supporting?.let { { Text(it) } },
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        tonalElevation = 0.dp,
    )
}
