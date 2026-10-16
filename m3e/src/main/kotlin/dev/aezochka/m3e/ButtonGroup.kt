@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupMenuState
import androidx.compose.material3.ButtonGroupScope
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * ButtonGroup M3E: строка кнопок с автоматическим overflow —
 * не влезающие прячутся под кнопку меню [overflowIndicator].
 */
@Composable
fun M3EButtonGroup(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    overflowIndicator: @Composable (ButtonGroupMenuState) -> Unit = { menuState ->
        FilledIconButton(
            onClick = {
                if (menuState.isExpanded) menuState.dismiss() else menuState.show()
            },
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Ещё")
        }
    },
    content: ButtonGroupScope.() -> Unit,
) {
    ButtonGroup(
        overflowIndicator = overflowIndicator,
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content,
    )
}
