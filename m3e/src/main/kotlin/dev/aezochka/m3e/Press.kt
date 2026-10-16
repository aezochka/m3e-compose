package dev.aezochka.m3e

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Пружинный press: спад масштаба + морф угла (гладкий → «сжатый»),
 * вместо обычного ripple. Фирменный эффект M3E из прототипа клавиатуры
 * и Glimpse-плиток, вынесенный сюда один раз.
 */
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.m3ePress(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    scaleTo: Float = 0.94f,
    corner: Dp = 10.dp,
    cornerPressed: Dp = 26.dp,
): Modifier = composed {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) scaleTo else 1f,
        animationSpec = M3EMotion.spatialFast(),
        label = "m3ePressScale",
    )
    val cornerValue by animateDpAsState(
        targetValue = if (pressed) cornerPressed else corner,
        animationSpec = M3EMotion.spatialFast(),
        label = "m3ePressCorner",
    )
    this
        .graphicsLayer { scaleX = scale; scaleY = scale }
        .clip(RoundedCornerShape(cornerValue))
        .combinedClickable(
            interactionSource = interaction,
            indication = null,
            onClick = onClick,
            onLongClick = onLongClick,
        )
}

/** Пилюля/кнопка: пружинный спад без морфа угла. */
fun Modifier.m3ePillPress(
    onClick: () -> Unit,
    scaleTo: Float = 0.95f,
): Modifier = composed {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) scaleTo else 1f,
        animationSpec = M3EMotion.spatialFast(),
        label = "m3ePillScale",
    )
    this
        .graphicsLayer { scaleX = scale; scaleY = scale }
        .clickable(interactionSource = interaction, indication = null, onClick = onClick)
}

/**
 * Стэггерное появление: элементы всплывают пружинно, волной по [index]
 * (как поп-ин клавиш m3e-клавиатуры).
 */
fun Modifier.m3ePopIn(
    index: Int = 0,
    delayMillis: Long = 28L,
    fromScale: Float = 0.86f,
): Modifier = composed {
    var shown by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (shown) 1f else fromScale,
        animationSpec = M3EMotion.spatial(),
        label = "m3ePopScale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (shown) 1f else 0f,
        animationSpec = M3EMotion.effects(),
        label = "m3ePopAlpha",
    )
    LaunchedEffect(Unit) {
        delay(index * delayMillis)
        shown = true
    }
    graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha }
}
