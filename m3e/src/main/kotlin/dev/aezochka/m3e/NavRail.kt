@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailState
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Wide Navigation Rail M3E: рельс, который разворачивается/сворачивается
 * с пружинной анимацией (railState.toggle()) — системный паттерн
 * для планшетов/лендскейпа.
 */
@Composable
fun M3EWideNavigationRail(
    modifier: Modifier = Modifier,
    railState: WideNavigationRailState = rememberWideNavigationRailState(),
    header: @Composable () -> Unit = {},
    items: List<Pair<ImageVector, String>>,
    selected: Int,
    onSelect: (Int) -> Unit,
) {
    val expanded = railState.currentValue == WideNavigationRailValue.Expanded
    WideNavigationRail(
        modifier = modifier,
        state = railState,
        header = header,
    ) {
        Column {
            items.forEachIndexed { index, (icon, label) ->
                WideNavigationRailItem(
                    selected = selected == index,
                    onClick = { onSelect(index) },
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) },
                    railExpanded = expanded,
                )
            }
        }
    }
}
