@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Кнопка-переключатель с shape-морфом: в checked — пилюля,
 * в покое — обычные углы, при нажатии — сжатые.
 */
@Composable
fun M3EToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shapes: ToggleButtonShapes = m3eToggleShapes(),
    content: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit,
) {
    ToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        shapes = shapes,
        content = content,
    )
}

/** Морф-набор форм для toggle: idle → pressed → checked(пилюля). */
fun m3eToggleShapes(
    idle: Shape = RoundedCornerShape(20.dp),
    pressed: Shape = RoundedCornerShape(12.dp),
    checked: Shape = RoundedCornerShape(percent = 50),
): ToggleButtonShapes = ToggleButtonShapes(idle, pressed, checked)

/**
 * Connected-группа (радио-стиль M3E): кнопки прижаты друг к другу,
 * крайние скруглены снаружи, выбранный элемент морфится в пилюлю.
 * Порт паттерна «Connected Button Group» из M3E-спеки.
 */
@Composable
fun M3EConnectedToggleGroup(
    options: List<String>,
    selected: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    icons: List<ImageVector>? = null,
    checkedIcons: List<ImageVector>? = null,
) {
    val n = options.size
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        options.forEachIndexed { index, label ->
            val idle = when (index) {
                0 -> RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
                n - 1 -> RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
                else -> RoundedCornerShape(0.dp)
            }
            ToggleButton(
                checked = selected == index,
                onCheckedChange = { onSelect(index) },
                modifier = Modifier.weight(if (index == n / 2) 1.5f else 1f),
                shapes = m3eToggleShapes(idle = idle),
            ) {
                val icon = (if (selected == index) checkedIcons else icons)?.getOrNull(index)
                if (icon != null) {
                    androidx.compose.material3.Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                } else {
                    Text(label)
                }
            }
        }
    }
}

/** Сахар: connected-группа со своим состоянием. */
@Composable
fun M3EConnectedToggleGroup(
    options: List<String>,
    modifier: Modifier = Modifier,
    icons: List<ImageVector>? = null,
    checkedIcons: List<ImageVector>? = null,
    onSelected: (Int) -> Unit = {},
): Int {
    var selected by remember { mutableIntStateOf(0) }
    M3EConnectedToggleGroup(options, selected, { i -> selected = i; onSelected(i) }, modifier, icons, checkedIcons)
    return selected
}
