package dev.aezochka.m3e

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

/**
 * Один словарь движений на всё приложение — M3E-спринги с овершутом.
 * Портировано из прототипа m3e-keyboard: cubic-bezier(.34,1.45,.5,1) ≈ spring(0.62, MediumLow).
 *
 * Внутри [M3ETheme] это же подмешивает MotionScheme.expressive() в стандартные
 * компоненты material3 — а [M3EMotion] даёт тот же характер своим анимациям.
 */
object M3EMotion {
    fun <T> spatial(): FiniteAnimationSpec<T> =
        spring(dampingRatio = 0.62f, stiffness = Spring.StiffnessMediumLow)

    fun <T> spatialFast(): FiniteAnimationSpec<T> =
        spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMedium)

    fun <T> effects(): FiniteAnimationSpec<T> =
        tween(190, easing = CubicBezierEasing(0.2f, 0f, 0f, 1f))
}
