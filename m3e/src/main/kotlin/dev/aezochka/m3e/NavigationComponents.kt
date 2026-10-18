@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Single-choice M3E segmented control with correctly joined edge shapes. */
@Composable
fun M3ESegmentedButtonRow(
    labels: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    require(labels.isNotEmpty()) { "M3ESegmentedButtonRow requires at least one label" }
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        labels.forEachIndexed { index, label ->
            SegmentedButton(
                selected = selectedIndex == index,
                onClick = { onSelect(index) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = labels.size),
                enabled = enabled,
            ) {
                Text(label)
            }
        }
    }
}

data class M3EShortNavigationItem(
    val label: String,
    val icon: ImageVector,
)

/** Material 3 Expressive short navigation bar and its animated selection indicators. */
@Composable
fun M3EShortNavigationBar(
    items: List<M3EShortNavigationItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    require(items.isNotEmpty()) { "M3EShortNavigationBar requires at least one item" }
    require(selectedIndex in items.indices) { "selectedIndex must refer to an item" }
    ShortNavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            ShortNavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onSelect(index) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
            )
        }
    }
}
