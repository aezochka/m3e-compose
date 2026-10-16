@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon

/**
 * Форма, морфящаяся между двумя полигонами по progress() ∈ [0..1].
 * Рецепт как у Google в MaterialShapes.toShape: путь в юнит-пространстве
 * → scale на size → центрирование.
 */
class M3EMorphShape(
    private val morph: Morph,
    private val progress: () -> Float,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density,
    ): Outline {
        val path = Path()
        val cubics = morph.asCubics(progress().coerceIn(0f, 1f))
        if (cubics.isNotEmpty()) {
            val w = size.width
            val h = size.height
            path.moveTo(cubics[0].anchor0X * w, cubics[0].anchor0Y * h)
            for (c in cubics) {
                path.cubicTo(
                    c.control0X * w, c.control0Y * h,
                    c.control1X * w, c.control1Y * h,
                    c.anchor1X * w, c.anchor1Y * h,
                )
            }
            path.close()
        }
        // центрируем, как в оригинале
        val bounds = path.getBounds()
        path.translate(Offset(size.width / 2f - bounds.center.x, size.height / 2f - bounds.center.y))
        return Outline.Generic(path)
    }
}

/**
 * Composable-обёртка: пересобирает Shape при смене progress,
 * чтобы clip/background пере-вычислили контур (каждый кадр морфа).
 */
@Composable
fun m3eMorphShape(
    from: RoundedPolygon,
    to: RoundedPolygon,
    progress: () -> Float,
): Shape {
    val morph = remember(from, to) { Morph(from, to) }
    // читаем progress, чтобы рекомпозиция шла каждый кадр анимации
    val p = progress()
    return remember(morph, p) { M3EMorphShape(morph) { p } }
}

/**
 * Морф-плитка: в покое один полигон (пилюля), при нажатии — второй
 * (печенька/клевер), переход — пружинный. Shape-морф нажатия из M3E-спеки.
 */
@Composable
fun M3EMorphTile(
    label: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    idle: RoundedPolygon = MaterialShapes.Pill,
    pressed: RoundedPolygon = MaterialShapes.Cookie9Sided,
    size: Dp = 96.dp,
    icon: ImageVector? = null,
) {
    val interaction = remember { MutableInteractionSource() }
    val isPressed by interaction.collectIsPressedAsState()
    val progress by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        animationSpec = M3EMotion.spatialFast(),
        label = "m3eMorph",
    )
    val shape = m3eMorphShape(idle, pressed) { progress }
    val scheme = MaterialTheme.colorScheme
    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                val s = 1f - 0.04f * progress
                scaleX = s; scaleY = s
            }
            .clip(shape)
            .background(scheme.primaryContainer)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = label, tint = scheme.onPrimaryContainer)
        } else {
            Text(label, color = scheme.onPrimaryContainer)
        }
    }
}
