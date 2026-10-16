@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupMenuState
import androidx.compose.material3.ButtonGroupScope
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuGroupShapes
import androidx.compose.material3.MenuItemShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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
                if (menuState.isShowing) menuState.dismiss() else menuState.show()
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

/** Группа меню M3E: скруглённый контейнер-«остров» вместо плоского списка. */
@Composable
fun M3EMenuGroup(
    modifier: Modifier = Modifier,
    shapes: androidx.compose.material3.MenuGroupShapes = MenuGroupShapes(
        shape = RoundedCornerShape(20.dp),
        inactiveShape = RoundedCornerShape(8.dp),
    ),
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit,
) {
    androidx.compose.material3.DropdownMenuGroup(
        shapes = shapes,
        modifier = modifier,
        content = content,
    )
}

/** Пункт меню с иконкой. */
@Composable
fun M3EMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    androidx.compose.material3.DropdownMenuItem(
        text = { androidx.compose.material3.Text(text) },
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
        leadingIcon = if (leadingIcon != null) {
            { Icon(leadingIcon, contentDescription = null) }
        } else null,
        trailingContent = trailingContent,
    )
}

/** Чекбокс-пункт: выбранный морфится в пилюлю (selectedShape). */
@Composable
fun M3ECheckableMenuItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
) {
    androidx.compose.material3.CheckableDropdownMenuItem(
        text = { androidx.compose.material3.Text(text) },
        onCheckedChange = onCheckedChange,
        checked = checked,
        shapes = MenuItemShapes(
            shape = RoundedCornerShape(20.dp),
            selectedShape = RoundedCornerShape(percent = 50),
        ),
        modifier = modifier,
        leadingIcon = if (leadingIcon != null) {
            { Icon(leadingIcon, contentDescription = null) }
        } else null,
    )
}
