@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/** Пункт FAB-меню. */
data class M3EFabMenuItem(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit = {},
)

/**
 * FAB-меню M3E: тумблер-FAB (морфится при открытии) + выезжающие
 * пункты. BackHandler добавь снаружи, если нужен системный «назад».
 */
@Composable
fun M3EFabMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    items: List<M3EFabMenuItem>,
    modifier: Modifier = Modifier,
    menuAlignment: Alignment.Horizontal = Alignment.End,
) {
    Box(modifier = modifier) {
        FloatingActionButtonMenu(
            expanded = expanded,
            modifier = Modifier.align(Alignment.BottomEnd),
            content = {
                items.forEach { item ->
                    FloatingActionButtonMenuItem(
                        onClick = {
                            item.onClick()
                            onExpandedChange(false)
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        text = { androidx.compose.material3.Text(item.label) },
                    )
                }
            },
        )
        ToggleFloatingActionButton(
            checked = expanded,
            onCheckedChange = { onExpandedChange(it) },
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            androidx.compose.material3.Icon(
                if (expanded) androidx.compose.material.icons.Icons.Filled.Close
                else androidx.compose.material.icons.Icons.Filled.Add,
                contentDescription = if (expanded) "Закрыть" else "Открыть",
            )
        }
    }
}
