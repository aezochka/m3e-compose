@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.VibrantTimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Expressive TimePicker M3E: «Vibrant» — цветные контейнеры цифр,
 * пилюляный переключатель input/clock.
 */
@Composable
fun M3EVibrantTimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
    initialHour: Int = 9,
    initialMinute: Int = 0,
    is24Hour: Boolean = true,
) {
    val state = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = is24Hour,
    )
    VibrantTimePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { onConfirm(state.hour, state.minute) }) { Text("ОК") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Отмена") }
        },
    ) {
        TimePicker(state = state)
    }
}

/** Сахар: кнопка «выбрать время» + диалог состоянием. */
@Composable
fun M3ETimePickerField(
    label: (hour: Int, minute: Int) -> String = { h, m -> "%02d:%02d".format(h, m) },
    modifier: Modifier = Modifier,
    initialHour: Int = 9,
    initialMinute: Int = 0,
    is24Hour: Boolean = true,
): String {
    var time by remember { mutableStateOf(initialHour to initialMinute) }
    var open by remember { mutableStateOf(false) }
    M3EButton(onClick = { open = true }, modifier = modifier) {
        Text(label(time.first, time.second), modifier = Modifier.padding(horizontal = 8.dp))
    }
    if (open) {
        M3EVibrantTimePickerDialog(
            onDismiss = { open = false },
            onConfirm = { h, m -> time = h to m; open = false },
            initialHour = time.first,
            initialMinute = time.second,
            is24Hour = is24Hour,
        )
    }
    return "%02d:%02d".format(time.first, time.second)
}
