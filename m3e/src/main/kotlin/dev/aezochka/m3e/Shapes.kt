@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.Shapes
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Экспрессивная шкала углов (портирована с клавиатуры m3e-keyboard):
 * радиусы крупнее базовых M3 — «мягкие» поверхности expressive-языка.
 */
object M3ECornerScale {
    val extraSmall = 10.dp
    val small = 14.dp
    val medium = 18.dp
    val large = 26.dp
    val extraLarge = 34.dp
}

/** Shapes темы в M3E-шкале — отдать в [M3ETheme]. */
fun m3eShapes(): Shapes = Shapes(
    extraSmall = RoundedCornerShape(M3ECornerScale.extraSmall),
    small = RoundedCornerShape(M3ECornerScale.small),
    medium = RoundedCornerShape(M3ECornerScale.medium),
    large = RoundedCornerShape(M3ECornerScale.large),
    extraLarge = RoundedCornerShape(M3ECornerScale.extraLarge),
)

/**
 * Материальные формы из MaterialShapes для акцентных поверхностей.
 * toShape() — composable, поэтому функции, не значения.
 */
object M3EMaterial {
    /** Пилюля — spacebar, кнопки-«капсулы». */
    @Composable
    fun pill(): Shape = MaterialShapes.Pill.toShape()

    /** Печенька 9-угольная — акцентные пузыри, бейджи. */
    @Composable
    fun cookie(): Shape = MaterialShapes.Cookie9Sided.toShape()

    /** Клевер 4-листный. */
    @Composable
    fun clover(): Shape = MaterialShapes.Clover4Leaf.toShape()

    /** Солнце — бёрсты, «живые» акценты. */
    @Composable
    fun sunny(): Shape = MaterialShapes.Sunny.toShape()
}
