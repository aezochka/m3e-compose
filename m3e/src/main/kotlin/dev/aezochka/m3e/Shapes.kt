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

    /** Взрыв — для активных состояний. */
    @Composable
    fun burst(): Shape = MaterialShapes.SoftBurst.toShape()
}

/**
 * Полная библиотека MaterialShapes (35 форм) — «shape library ready to use»
 * из M3E. Выбирай по имени, получай [Shape].
 */
enum class M3EShape {
    CIRCLE, SQUARE, SLANTED, ARCH, FAN, ARROW, SEMI_CIRCLE, OVAL, PILL,
    TRIANGLE, DIAMOND, CLAM_SHELL, PENTAGON, GEM, SUNNY, VERY_SUNNY,
    COOKIE_4, COOKIE_6, COOKIE_7, COOKIE_9, COOKIE_12, GHOSTISH,
    CLOVER_4, CLOVER_8, BURST, SOFT_BURST, BOOM, SOFT_BOOM, FLOWER,
    PUFFY, PUFFY_DIAMOND, PIXEL_CIRCLE, PIXEL_TRIANGLE, BUN, HEART;

    /** Форма как Shape (composable — внутри композиции). */
    @androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
    @Composable
    fun shape(): Shape = when (this) {
        CIRCLE -> MaterialShapes.Circle
        SQUARE -> MaterialShapes.Square
        SLANTED -> MaterialShapes.Slanted
        ARCH -> MaterialShapes.Arch
        FAN -> MaterialShapes.Fan
        ARROW -> MaterialShapes.Arrow
        SEMI_CIRCLE -> MaterialShapes.SemiCircle
        OVAL -> MaterialShapes.Oval
        PILL -> MaterialShapes.Pill
        TRIANGLE -> MaterialShapes.Triangle
        DIAMOND -> MaterialShapes.Diamond
        CLAM_SHELL -> MaterialShapes.ClamShell
        PENTAGON -> MaterialShapes.Pentagon
        GEM -> MaterialShapes.Gem
        SUNNY -> MaterialShapes.Sunny
        VERY_SUNNY -> MaterialShapes.VerySunny
        COOKIE_4 -> MaterialShapes.Cookie4Sided
        COOKIE_6 -> MaterialShapes.Cookie6Sided
        COOKIE_7 -> MaterialShapes.Cookie7Sided
        COOKIE_9 -> MaterialShapes.Cookie9Sided
        COOKIE_12 -> MaterialShapes.Cookie12Sided
        GHOSTISH -> MaterialShapes.Ghostish
        CLOVER_4 -> MaterialShapes.Clover4Leaf
        CLOVER_8 -> MaterialShapes.Clover8Leaf
        BURST -> MaterialShapes.Burst
        SOFT_BURST -> MaterialShapes.SoftBurst
        BOOM -> MaterialShapes.Boom
        SOFT_BOOM -> MaterialShapes.SoftBoom
        FLOWER -> MaterialShapes.Flower
        PUFFY -> MaterialShapes.Puffy
        PUFFY_DIAMOND -> MaterialShapes.PuffyDiamond
        PIXEL_CIRCLE -> MaterialShapes.PixelCircle
        PIXEL_TRIANGLE -> MaterialShapes.PixelTriangle
        BUN -> MaterialShapes.Bun
        HEART -> MaterialShapes.Heart
    }.toShape()
}
