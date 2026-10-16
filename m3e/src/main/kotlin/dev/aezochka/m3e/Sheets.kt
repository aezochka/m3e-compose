@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * In-layout Bottom Sheet M3E: шторка прямо в разметке (не модальная),
 * с maxWidth-ограничением, жестами и dragHandle. Гибкая — влезает
 * в ограниченную колонку на планшете. Для модальной — [M3ESheet].
 */
@Composable
fun M3EFlexibleSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    state: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
    content: @Composable ColumnScope.() -> Unit,
) {
    BottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        state = state,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        content = content,
    )
}
