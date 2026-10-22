@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Морф кнопки при нажатии: pill в покое → «сжатый» угол под пальцем.
 * Button в material3 умеет ButtonShapes, но по умолчанию НЕ морфится —
 * этот дефолт и есть недостающий кусок порта.
 */
fun m3eButtonShapes(
    idleCornerPercent: Int = 50,
    pressedCorner: Dp = 12.dp,
): ButtonShapes = ButtonShapes(
    shape = RoundedCornerShape(percent = idleCornerPercent),
    pressedShape = RoundedCornerShape(pressedCorner),
)

/** Пять размеров кнопок M3E (XS 32dp … XL 136dp) — высота, паддинги, шрифт и иконка из спеки. */
enum class M3EButtonSize { ExtraSmall, Small, Medium, Large, ExtraLarge }

/** Высота контейнера для размера (токены ButtonDefaults). */
fun M3EButtonSize.height(): Dp = when (this) {
    M3EButtonSize.ExtraSmall -> ButtonDefaults.ExtraSmallContainerHeight
    M3EButtonSize.Small -> ButtonDefaults.MinHeight
    M3EButtonSize.Medium -> ButtonDefaults.MediumContainerHeight
    M3EButtonSize.Large -> ButtonDefaults.LargeContainerHeight
    M3EButtonSize.ExtraLarge -> ButtonDefaults.ExtraLargeContainerHeight
}

/** Размер иконки внутри кнопки этого размера. */
fun M3EButtonSize.iconSize(): Dp = ButtonDefaults.iconSizeFor(height())

/** Морф-набор для размера: спековый pressed-угол растёт вместе с кнопкой. */
@Composable
fun M3EButtonSize.shapes(): ButtonShapes =
    if (this == M3EButtonSize.Small) m3eButtonShapes() else ButtonDefaults.shapesFor(height())

private enum class Kind { Filled, Tonal, Outlined, Elevated, Text }

@Composable
private fun M3EButtonImpl(
    kind: Kind,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    size: M3EButtonSize,
    shapes: ButtonShapes?,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit,
) {
    val h = size.height()
    val s = shapes ?: size.shapes()
    val m = modifier.heightIn(min = h)
    val padding = ButtonDefaults.contentPaddingFor(h)
    val sized: @Composable RowScope.() -> Unit = {
        val scope = this
        ProvideTextStyle(ButtonDefaults.textStyleFor(h)) { scope.content() }
    }
    when (kind) {
        Kind.Filled -> Button(onClick = onClick, shapes = s, modifier = m, enabled = enabled, colors = colors, contentPadding = padding, content = sized)
        Kind.Tonal -> FilledTonalButton(onClick = onClick, shapes = s, modifier = m, enabled = enabled, colors = colors, contentPadding = padding, content = sized)
        Kind.Outlined -> OutlinedButton(onClick = onClick, shapes = s, modifier = m, enabled = enabled, colors = colors, contentPadding = padding, content = sized)
        Kind.Elevated -> ElevatedButton(onClick = onClick, shapes = s, modifier = m, enabled = enabled, colors = colors, contentPadding = padding, content = sized)
        Kind.Text -> TextButton(onClick = onClick, shapes = s, modifier = m, enabled = enabled, colors = colors, contentPadding = padding, content = sized)
    }
}

@Composable
fun M3EButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    size: M3EButtonSize = M3EButtonSize.Small,
    shapes: ButtonShapes? = null,
    content: @Composable RowScope.() -> Unit,
) = M3EButtonImpl(Kind.Filled, onClick, modifier, enabled, size, shapes, colors, content)

@Composable
fun M3ETonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    size: M3EButtonSize = M3EButtonSize.Small,
    shapes: ButtonShapes? = null,
    content: @Composable RowScope.() -> Unit,
) = M3EButtonImpl(Kind.Tonal, onClick, modifier, enabled, size, shapes, colors, content)

@Composable
fun M3EOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    size: M3EButtonSize = M3EButtonSize.Small,
    shapes: ButtonShapes? = null,
    content: @Composable RowScope.() -> Unit,
) = M3EButtonImpl(Kind.Outlined, onClick, modifier, enabled, size, shapes, colors, content)

@Composable
fun M3EElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    size: M3EButtonSize = M3EButtonSize.Small,
    shapes: ButtonShapes? = null,
    content: @Composable RowScope.() -> Unit,
) = M3EButtonImpl(Kind.Elevated, onClick, modifier, enabled, size, shapes, colors, content)

@Composable
fun M3ETextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    size: M3EButtonSize = M3EButtonSize.Small,
    shapes: ButtonShapes? = null,
    content: @Composable RowScope.() -> Unit,
) = M3EButtonImpl(Kind.Text, onClick, modifier, enabled, size, shapes, colors, content)
