@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Морф кнопки при нажатии: pill в покое → «сжатый» угол под пальцем.
 * В material3 1.5.0-beta01 Button умеет ButtonShapes, но по умолчанию НЕ морфится —
 * этот дефолт и есть недостающий кусок порта.
 */
fun m3eButtonShapes(
    idleCornerPercent: Int = 50,
    pressedCorner: Dp = 12.dp,
): ButtonShapes = ButtonShapes(
    shape = RoundedCornerShape(percent = idleCornerPercent),
    pressedShape = RoundedCornerShape(pressedCorner),
)

@Composable
fun M3EButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shapes = m3eButtonShapes(),
        colors = colors,
        content = content,
    )
}

@Composable
fun M3ETonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    content: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shapes = m3eButtonShapes(),
        colors = colors,
        content = content,
    )
}

@Composable
fun M3EOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    content: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shapes = m3eButtonShapes(),
        colors = colors,
        content = content,
    )
}
