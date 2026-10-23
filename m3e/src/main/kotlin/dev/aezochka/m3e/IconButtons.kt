@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/** Вариант заливки иконочной кнопки. */
enum class M3EIconButtonStyle { Standard, Filled, Tonal, Outlined }

/** Ширина иконочной кнопки M3E: узкая / квадратная / широкая. */
enum class M3EIconButtonWidth { Narrow, Uniform, Wide }

private fun M3EIconButtonWidth.option(): IconButtonDefaults.IconButtonWidthOption = when (this) {
    M3EIconButtonWidth.Narrow -> IconButtonDefaults.IconButtonWidthOption.Narrow
    M3EIconButtonWidth.Uniform -> IconButtonDefaults.IconButtonWidthOption.Uniform
    M3EIconButtonWidth.Wide -> IconButtonDefaults.IconButtonWidthOption.Wide
}

/** Контейнер и иконка по размеру — токены IconButtonDefaults (XS…XL). */
fun M3EButtonSize.iconButtonContainer(width: M3EIconButtonWidth = M3EIconButtonWidth.Uniform): DpSize = when (this) {
    M3EButtonSize.ExtraSmall -> IconButtonDefaults.extraSmallContainerSize(width.option())
    M3EButtonSize.Small -> IconButtonDefaults.smallContainerSize(width.option())
    M3EButtonSize.Medium -> IconButtonDefaults.mediumContainerSize(width.option())
    M3EButtonSize.Large -> IconButtonDefaults.largeContainerSize(width.option())
    M3EButtonSize.ExtraLarge -> IconButtonDefaults.extraLargeContainerSize(width.option())
}

/**
 * Размер иконки по размеру кнопки.
 *
 * Константы `IconButtonDefaults.*IconSize` есть не во всех сборках material3
 * (в резолве CI их не было → Unresolved reference), поэтому держим свои Dp.
 */
fun M3EButtonSize.iconButtonIcon(): Dp = when (this) {
    M3EButtonSize.ExtraSmall -> 20.dp
    M3EButtonSize.Small -> 24.dp
    M3EButtonSize.Medium -> 24.dp
    M3EButtonSize.Large -> 32.dp
    M3EButtonSize.ExtraLarge -> 40.dp
}

/**
 * Иконочная кнопка M3E: пять размеров × три ширины × четыре заливки,
 * круг в покое → спековый pressed-угол (IconButtonShapes морфит сама material3).
 */
@Composable
fun M3EIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: M3EIconButtonStyle = M3EIconButtonStyle.Standard,
    size: M3EButtonSize = M3EButtonSize.Small,
    width: M3EIconButtonWidth = M3EIconButtonWidth.Uniform,
    enabled: Boolean = true,
) {
    val m = modifier.size(size.iconButtonContainer(width))
    val shapes = IconButtonDefaults.shapes()
    val content: @Composable () -> Unit = {
        Icon(icon, contentDescription, Modifier.size(size.iconButtonIcon()))
    }
    when (style) {
        M3EIconButtonStyle.Standard -> IconButton(onClick = onClick, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Filled -> FilledIconButton(onClick = onClick, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Tonal -> FilledTonalIconButton(onClick = onClick, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Outlined -> OutlinedIconButton(onClick = onClick, shapes = shapes, modifier = m, enabled = enabled, content = content)
    }
}

/**
 * Иконочный тумблер M3E: idle-круг → pressed → checked-квадрат (toggleableShapes),
 * опционально другая иконка во включённом состоянии.
 */
@Composable
fun M3EIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    checkedIcon: ImageVector = icon,
    style: M3EIconButtonStyle = M3EIconButtonStyle.Tonal,
    size: M3EButtonSize = M3EButtonSize.Small,
    width: M3EIconButtonWidth = M3EIconButtonWidth.Uniform,
    enabled: Boolean = true,
) {
    val m = modifier.size(size.iconButtonContainer(width))
    val shapes = IconButtonDefaults.toggleableShapes()
    val content: @Composable () -> Unit = {
        Icon(if (checked) checkedIcon else icon, contentDescription, Modifier.size(size.iconButtonIcon()))
    }
    when (style) {
        M3EIconButtonStyle.Standard -> IconToggleButton(checked = checked, onCheckedChange = onCheckedChange, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Filled -> FilledIconToggleButton(checked = checked, onCheckedChange = onCheckedChange, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Tonal -> FilledTonalIconToggleButton(checked = checked, onCheckedChange = onCheckedChange, shapes = shapes, modifier = m, enabled = enabled, content = content)
        M3EIconButtonStyle.Outlined -> OutlinedIconToggleButton(checked = checked, onCheckedChange = onCheckedChange, shapes = shapes, modifier = m, enabled = enabled, content = content)
    }
}
