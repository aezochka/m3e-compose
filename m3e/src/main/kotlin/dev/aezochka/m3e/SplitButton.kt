@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * SplitButton M3E: основное действие + стрелка-шеврон, которая
 * морфится (проворачивается) при раскрытии. Паттерн из M3E-спеки.
 */
@Composable
fun M3ESplitButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    expanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = {},
    trailingIcon: ImageVector = Icons.Filled.KeyboardArrowDown,
) {
    // controlled, если снаружи дают onExpandedChange; внутреннее состояние — только фолбэк
    var internal by remember { mutableStateOf(expanded) }
    androidx.compose.runtime.LaunchedEffect(expanded) { internal = expanded }
    val checked = internal
    SplitButtonLayout(
        modifier = modifier,
        leadingButton = {
            SplitButtonDefaults.LeadingButton(onClick = onClick) {
                if (leadingIcon != null) {
                    Icon(
                        leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
                Text(text)
            }
        },
        trailingButton = {
            SplitButtonDefaults.TrailingButton(
                checked = checked,
                onCheckedChange = {
                    internal = it
                    onExpandedChange(it)
                },
            ) {
                val rotation by animateFloatAsState(
                    targetValue = if (checked) 180f else 0f,
                    animationSpec = M3EMotion.spatialFast(),
                    label = "m3eSplitArrow",
                )
                Icon(
                    trailingIcon,
                    contentDescription = if (checked) "Свернуть" else "Раскрыть",
                    modifier = Modifier
                        .size(SplitButtonDefaults.TrailingIconSize)
                        .graphicsLayer { rotationZ = rotation },
                )
            }
        },
    )
}
