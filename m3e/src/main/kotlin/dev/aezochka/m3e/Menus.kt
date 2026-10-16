@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CheckableDropdownMenuItem
import androidx.compose.material3.DropdownMenuGroup
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuGroupShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Группа меню M3E: скруглённый контейнер-«остров» вместо плоского списка.
 */
@Composable
fun M3EMenuGroup(
    modifier: Modifier = Modifier,
    shapes: MenuGroupShapes = MenuGroupShapes(),
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenuGroup(
        modifier = modifier,
        shapes = shapes,
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
    trailingIcon: ImageVector? = null,
) {
    DropdownMenuItem(
        text = { androidx.compose.material3.Text(text) },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = if (leadingIcon != null) {
            { Icon(leadingIcon, contentDescription = null) }
        } else null,
        trailingIcon = if (trailingIcon != null) {
            { Icon(trailingIcon, contentDescription = null) }
        } else null,
    )
}

/** Чекбокс-пункт (M3E: галочка появляется с морфом). */
@Composable
fun M3ECheckableMenuItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
) {
    CheckableDropdownMenuItem(
        text = { androidx.compose.material3.Text(text) },
        onCheckedChange = onCheckedChange,
        checked = checked,
        modifier = modifier,
        leadingIcon = if (leadingIcon != null) {
            { Icon(leadingIcon, contentDescription = null) }
        } else null,
    )
}
