@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import kotlin.math.min

/** Синусная волна-разделитель из прототипа m3e (подчёркивание саггестов). */
@Composable
fun M3EWavyDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    thickness: Dp = 3.dp,
    wavelength: Dp = 28.dp,
    amplitude: Dp = 2.dp,
    progress: Float = 1f,
) {
    val h = thickness + amplitude * 2
    Canvas(modifier = modifier.fillMaxWidth().height(h)) {
        val mid = size.height / 2f
        val amp = amplitude.toPx()
        val seg = wavelength.toPx()
        val stopX = size.width * progress.coerceIn(0f, 1f)
        val path = Path()
        path.moveTo(0f, mid)
        var x = 0f
        var up = true
        while (x < stopX) {
            val next = min(x + seg, stopX)
            path.quadraticBezierTo(x + seg / 2f, if (up) mid - amp else mid + amp, next, mid)
            x = next
            up = !up
        }
        drawPath(path, color, style = Stroke(width = thickness.toPx()))
    }
}

/** Линейный wavy-прогресс с тематическими цветами. */
@Composable
fun M3ELinearWavyProgress(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    LinearWavyProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

/** Круговой wavy-прогресс с тематическими цветами. */
@Composable
fun M3ECircularWavyProgress(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    CircularWavyProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

/** M3E LoadingIndicator (пульсирующие волны) с тематическим цветом. */
@Composable
fun M3ELoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    LoadingIndicator(
        modifier = modifier,
        color = color,
    )
}

/** Contained-версия: индикатор в своей капсуле-контейнере. */
@Composable
fun M3EContainedLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
) {
    androidx.compose.material3.ContainedLoadingIndicator(
        modifier = modifier,
        color = color,
        containerColor = containerColor,
    )
}

/** Недетерминированная wavy-полоса (поиск/загрузка без прогресса). */
@Composable
fun M3EWavyLinearLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    androidx.compose.material3.LinearWavyProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

/** Недетерминированный круговой wavy. */
@Composable
fun M3EWavyCircularLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    androidx.compose.material3.CircularWavyProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}
