@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.InteractiveListItem
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Expressive list item: 16dp форма, при нажатии морф в 28dp,
 * выбранный — пилюля. Слот-структура как у классического ListItem.
 */
@Composable
fun M3EListItem(
    headline: String,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    selected: Boolean = false,
    onClick: () -> Unit = {},
) {
    InteractiveListItem(
        modifier = modifier,
        onClick = onClick,
        selected = selected,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overline?.let { overlineText -> { Text(overlineText) } },
        supportingContent = supporting?.let { supportingText -> { Text(supportingText) } },
        shapes = ListItemShapes(
            shape = RoundedCornerShape(16.dp),
            pressedShape = RoundedCornerShape(28.dp),
            focusedShape = RoundedCornerShape(28.dp),
            hoveredShape = RoundedCornerShape(24.dp),
            selectedShape = RoundedCornerShape(percent = 50),
            disabledShape = RoundedCornerShape(16.dp),
        ),
    ) {
        Text(headline)
    }
}
