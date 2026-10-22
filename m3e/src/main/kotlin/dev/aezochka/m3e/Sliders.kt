@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.VerticalSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
private fun rememberSyncedSliderState(
    value: Float,
    steps: Int,
    valueRange: ClosedFloatingPointRange<Float>,
): SliderState {
    val state = remember(steps, valueRange) { SliderState(value, steps, valueRange) }
    // внешнее значение — источник правды: синхронизируем, если поменяли снаружи
    LaunchedEffect(value) { if (state.value != value) state.value = value }
    return state
}

/**
 * Слайдер M3E (толстый трек со stop-индикаторами и ручкой-палочкой) на state-API.
 * value-оверлоад Slider в beta02 помечен deprecated — этот обходит его.
 */
@Composable
fun M3ESlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    val state = rememberSyncedSliderState(value, steps, valueRange)
    Slider(
        state = state,
        onValueChange = { state.value = it; onValueChange(it) },
        modifier = modifier,
        enabled = enabled,
        onValueChangeFinished = onValueChangeFinished,
    )
}

/** Вертикальный слайдер M3E (громкость/яркость). Высоту задай модификатором. */
@Composable
fun M3EVerticalSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    topToBottom: Boolean = false,
) {
    val state = rememberSyncedSliderState(value, steps, valueRange)
    VerticalSlider(
        state = state,
        onValueChange = { state.value = it; onValueChange(it) },
        modifier = modifier,
        enabled = enabled,
        topToBottom = topToBottom,
    )
}
