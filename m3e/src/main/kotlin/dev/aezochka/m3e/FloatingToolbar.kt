@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.FloatingToolbarScrollBehavior
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Плавающая тулбар-пилюля M3E: прячется по скроллу
 * ([scrollBehavior] из FloatingToolbarDefaults.enterAlwaysScrollBehavior()),
 * разворачивается (expanded) с morph-анимацией.
 */
@Composable
fun M3EHorizontalFloatingToolbar(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: FloatingToolbarScrollBehavior? = null,
    leadingContent: @Composable RowScope.() -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    HorizontalFloatingToolbar(
        expanded = expanded,
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        content = content,
    )
}

/** Вертикальный вариант: боковая пилюля, good для режимов/инструментов. */
@Composable
fun M3EVerticalFloatingToolbar(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: FloatingToolbarScrollBehavior? = null,
    leadingContent: @Composable ColumnScope.() -> Unit = {},
    trailingContent: @Composable ColumnScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    VerticalFloatingToolbar(
        expanded = expanded,
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        content = content,
    )
}
