package dev.aezochka.m3e

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.Shapes
import androidx.compose.material3.toShape
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

/** Материальные формы из MaterialShapes для акцентных поверхностей. */
object M3EMaterial {
    /** Пилюля — spacebar, кнопки-«капсулы». */
    val pill: Shape = MaterialShapes.Pill.toShape()

    /** Печенька 9-угольная — акцентные пузыри, бейджи. */
    val cookie: Shape = MaterialShapes.Cookie9Sided.toShape()

    /** Клевер 4-листный. */
    val clover: Shape = MaterialShapes.Clover4Sided.toShape()
}
