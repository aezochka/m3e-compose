@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e.demo.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.M3EAppBarAction
import dev.aezochka.m3e.M3EAppBarRow
import dev.aezochka.m3e.M3EButton
import dev.aezochka.m3e.M3EButtonSize
import dev.aezochka.m3e.M3EElevatedButton
import dev.aezochka.m3e.M3EExtendedFab
import dev.aezochka.m3e.M3EFab
import dev.aezochka.m3e.M3EFabSize
import dev.aezochka.m3e.M3EIconButton
import dev.aezochka.m3e.M3EIconButtonStyle
import dev.aezochka.m3e.M3EIconButtonWidth
import dev.aezochka.m3e.M3EIconToggleButton
import dev.aezochka.m3e.M3ESegmentedButtonRow
import dev.aezochka.m3e.M3ESlider
import dev.aezochka.m3e.M3ESwitch
import dev.aezochka.m3e.M3ETextButton
import dev.aezochka.m3e.M3ETooltip
import dev.aezochka.m3e.M3EVerticalSlider

@Composable
private fun Title(t: String) = Text(t, style = MaterialTheme.typography.titleMedium)

@Composable
internal fun ButtonSizesScreen() {
    Title("Пять размеров (XS → XL)")
    M3EButtonSize.entries.forEach { s ->
        M3EButton(onClick = {}, size = s) { Text(s.name) }
    }
    Title("Elevated / Text")
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        M3EElevatedButton(onClick = {}) { Text("Elevated") }
        M3ETextButton(onClick = {}) { Text("Text") }
    }
}

@Composable
internal fun IconButtonsScreen() {
    var style by remember { mutableStateOf(1) }
    var width by remember { mutableStateOf(1) }
    var fav by remember { mutableStateOf(false) }
    var saved by remember { mutableStateOf(true) }
    M3ESegmentedButtonRow(listOf("Std", "Filled", "Tonal", "Outl."), style, { style = it }, Modifier.fillMaxWidth())
    M3ESegmentedButtonRow(listOf("Narrow", "Uniform", "Wide"), width, { width = it }, Modifier.fillMaxWidth())
    val st = M3EIconButtonStyle.entries[style]
    val w = M3EIconButtonWidth.entries[width]
    Row(
        Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        M3EButtonSize.entries.forEach { s ->
            M3EIconButton(Icons.Filled.Edit, s.name, onClick = {}, style = st, size = s, width = w)
        }
    }
    Title("Тумблеры: круг → квадрат")
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        M3EIconToggleButton(fav, { fav = it }, Icons.Outlined.FavoriteBorder, "Избранное", checkedIcon = Icons.Filled.Favorite, size = M3EButtonSize.Medium)
        M3EIconToggleButton(saved, { saved = it }, Icons.Outlined.BookmarkBorder, "Сохранить", checkedIcon = Icons.Filled.Bookmark, style = M3EIconButtonStyle.Filled, size = M3EButtonSize.Medium, width = M3EIconButtonWidth.Wide)
    }
}

@Composable
internal fun FabsScreen() {
    var expanded by remember { mutableStateOf(true) }
    Title("FAB: regular / medium / large")
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        M3EFabSize.entries.forEach { M3EFab(Icons.Filled.Add, it.name, onClick = {}, size = it) }
    }
    Title("Extended — схлопывается")
    M3ESwitch(expanded, { expanded = it })
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        M3EFabSize.entries.forEach { M3EExtendedFab("Создать", Icons.Filled.Edit, onClick = {}, expanded = expanded, size = it) }
    }
}

@Composable
internal fun SlidersScreen() {
    var a by remember { mutableFloatStateOf(0.4f) }
    var b by remember { mutableFloatStateOf(3f) }
    var v by remember { mutableFloatStateOf(0.6f) }
    var sw by remember { mutableStateOf(true) }
    Title("Slider: ${(a * 100).toInt()}%")
    M3ESlider(a, { a = it })
    Title("Шаги: ${b.toInt()}")
    M3ESlider(b, { b = it }, valueRange = 0f..5f, steps = 4)
    Row(horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
        M3EVerticalSlider(v, { v = it }, Modifier.height(180.dp))
        Column {
            Text("Вертикальный: ${(v * 100).toInt()}%")
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                M3ESwitch(sw, { sw = it }); Text(if (sw) "Вкл" else "Выкл")
            }
        }
    }
}

@Composable
internal fun ActionsScreen() {
    var starred by remember { mutableStateOf(false) }
    Title("AppBarRow: лишнее — в «⋮»")
    M3EAppBarRow(
        actions = listOf(
            M3EAppBarAction(Icons.Filled.Share, "Поделиться"),
            M3EAppBarAction(Icons.Outlined.Star, "В избранное", checked = starred, onCheckedChange = { starred = it }),
            M3EAppBarAction(Icons.Filled.Edit, "Изменить"),
            M3EAppBarAction(Icons.Outlined.Delete, "Удалить"),
            M3EAppBarAction(Icons.Outlined.Info, "Сведения"),
        ),
        maxItemCount = 3,
    )
    Title("Tooltip (долгое нажатие)")
    M3ETooltip("Подсказка M3") {
        M3EIconButton(Icons.Outlined.Info, "Инфо", onClick = {}, style = M3EIconButtonStyle.Tonal)
    }
}
