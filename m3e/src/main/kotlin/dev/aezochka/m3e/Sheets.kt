@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Flexible Bottom Sheet M3E: шторка с [expandedContent] — контентом,
 * который раскрывается из шторки в полное «полотно» по мере вытягивания.
 * Это фирменный M3E-паттерн «sheet → expanded canvas».
 */
@Composable
fun M3EFlexibleSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
    expandedContent: (@Composable (Boolean) -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    BottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        expandedContent = expandedContent,
        content = content,
    )
}
