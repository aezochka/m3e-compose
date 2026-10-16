@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scrim
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

/**
 * Уровень контейнера — M3E-система контейнерных поверхностей:
 * группируй контентом по тонам, от surfaceContainerLowest до Highest.
 */
enum class M3EContainerLevel {
    LOWEST, LOW, NORMAL, HIGH, HIGHEST
}

@Composable
private fun containerColor(level: M3EContainerLevel): Color = when (level) {
    M3EContainerLevel.LOWEST -> MaterialTheme.colorScheme.surfaceContainerLowest
    M3EContainerLevel.LOW -> MaterialTheme.colorScheme.surfaceContainerLow
    M3EContainerLevel.NORMAL -> MaterialTheme.colorScheme.surfaceContainer
    M3EContainerLevel.HIGH -> MaterialTheme.colorScheme.surfaceContainerHigh
    M3EContainerLevel.HIGHEST -> MaterialTheme.colorScheme.surfaceContainerHighest
}

/**
 * M3E-контейнер: surface-container роль + expressive-форма.
 * С [onClick] — пружинный press с морфом угла (как плитки),
 * без — статичная поверхность для группировки контента.
 */
@Composable
fun M3EContainer(
    modifier: Modifier = Modifier,
    level: M3EContainerLevel = M3EContainerLevel.NORMAL,
    corner: Dp = M3ECornerScale.large,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val color = containerColor(level)
    if (onClick != null) {
        Box(
            modifier = modifier.m3ePress(
                onClick = onClick,
                corner = corner,
                cornerPressed = M3ECornerScale.extraLarge,
            ),
        ) {
            Surface(
                shape = RoundedCornerShape(corner),
                color = color,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ) { content() }
        }
    } else {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(corner),
            color = color,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) { content() }
    }
}

/** Карточка M3E: клик + крупный 28dp угол. */
@Composable
fun M3ECard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(28.dp),
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    content: @Composable () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) { content() }
}

/** Бейдж: маленький статус-индикатор. */
@Composable
fun M3EBadge(
    count: String? = null,
    modifier: Modifier = Modifier,
) {
    Badge(modifier = modifier) {
        if (count != null) Text(count)
    }
}

/** Обёртка: контент + бейдж в уголке. */
@Composable
fun M3EBadgedBox(
    badge: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BadgedBox(badge = badge, modifier = modifier, content = content)
}

/** Тонкий разделитель M3E. */
@Composable
fun M3EDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    HorizontalDivider(modifier = modifier, thickness = thickness, color = color)
}

@Composable
fun M3EVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    VerticalDivider(modifier = modifier, thickness = thickness, color = color)
}

/** Скрим для своих модалок (тонированная вуаль поверх контента). */
@Composable
fun M3EScrim(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Scrim(
        contentDescription = contentDescription,
        modifier = modifier,
        onClick = onClick,
    )
}
