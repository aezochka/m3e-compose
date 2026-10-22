@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeExtendedFloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MediumExtendedFloatingActionButton
import androidx.compose.material3.MediumFloatingActionButton
import androidx.compose.material3.SmallExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/** Размеры FAB в M3E: обычный 56, medium 80, large 96. */
enum class M3EFabSize { Regular, Medium, Large }

/** FAB M3E нужного размера с правильной иконкой (24 / 28 / 36dp). */
@Composable
fun M3EFab(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: M3EFabSize = M3EFabSize.Regular,
) {
    when (size) {
        M3EFabSize.Regular -> FloatingActionButton(onClick = onClick, modifier = modifier) {
            Icon(icon, contentDescription)
        }
        M3EFabSize.Medium -> MediumFloatingActionButton(onClick = onClick, modifier = modifier) {
            Icon(icon, contentDescription, Modifier.size(FloatingActionButtonDefaults.MediumIconSize))
        }
        M3EFabSize.Large -> LargeFloatingActionButton(onClick = onClick, modifier = modifier) {
            Icon(icon, contentDescription, Modifier.size(FloatingActionButtonDefaults.LargeIconSize))
        }
    }
}

/**
 * Extended FAB M3E (small / medium / large), схлопывается в иконку по [expanded] —
 * типично: expanded = листаем вверх.
 */
@Composable
fun M3EExtendedFab(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    size: M3EFabSize = M3EFabSize.Regular,
) {
    val t: @Composable () -> Unit = { Text(text) }
    when (size) {
        M3EFabSize.Regular -> SmallExtendedFloatingActionButton(
            text = t, icon = { Icon(icon, null) }, onClick = onClick, modifier = modifier, expanded = expanded,
        )
        M3EFabSize.Medium -> MediumExtendedFloatingActionButton(
            text = t, icon = { Icon(icon, null, Modifier.size(FloatingActionButtonDefaults.MediumIconSize)) },
            onClick = onClick, modifier = modifier, expanded = expanded,
        )
        M3EFabSize.Large -> LargeExtendedFloatingActionButton(
            text = t, icon = { Icon(icon, null, Modifier.size(FloatingActionButtonDefaults.LargeIconSize)) },
            onClick = onClick, modifier = modifier, expanded = expanded,
        )
    }
}
