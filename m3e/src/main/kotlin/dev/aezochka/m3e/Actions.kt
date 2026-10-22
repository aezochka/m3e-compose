@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/** Действие для [M3EAppBarRow]: обычное или тумблер (checked != null). */
data class M3EAppBarAction(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit = {},
    val checked: Boolean? = null,
    val onCheckedChange: (Boolean) -> Unit = {},
    val enabled: Boolean = true,
)

/**
 * Ряд действий app bar M3E с автоматическим overflow: что не влезло по ширине
 * (или сверх [maxItemCount]) уходит в меню «⋮» — подписи берутся из label.
 */
@Composable
fun M3EAppBarRow(
    actions: List<M3EAppBarAction>,
    modifier: Modifier = Modifier,
    maxItemCount: Int = Int.MAX_VALUE,
) {
    AppBarRow(
        modifier = modifier,
        maxItemCount = maxItemCount,
        overflowIndicator = { menu ->
            IconButton(onClick = { if (menu.isShowing) menu.dismiss() else menu.show() }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Ещё")
            }
        },
    ) {
        actions.forEach { a ->
            val iconSlot: @Composable () -> Unit = { Icon(a.icon, contentDescription = a.label) }
            val checked = a.checked
            if (checked != null) {
                toggleableItem(checked, a.onCheckedChange, iconSlot, a.label, a.enabled)
            } else {
                clickableItem(a.onClick, iconSlot, a.label, a.enabled)
            }
        }
    }
}

/** Подсказка по долгому нажатию/ховеру над [content] — PlainTooltip M3. */
@Composable
fun M3ETooltip(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            positioning = TooltipAnchorPosition.Above,
        ),
        tooltip = { PlainTooltip { Text(text) } },
        state = rememberTooltipState(),
        modifier = modifier,
        content = content,
    )
}

/** Свитч M3E: во включённом состоянии — галочка в ручке (как в системных настройках). */
@Composable
fun M3ESwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedIcon: ImageVector? = Icons.Filled.Check,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        thumbContent = if (checked && checkedIcon != null) {
            { Icon(checkedIcon, null, Modifier.size(SwitchDefaults.IconSize)) }
        } else null,
    )
}
