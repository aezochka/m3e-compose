package dev.aezochka.m3e.demo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.M3ESegmentedButtonRow
import dev.aezochka.m3e.M3EShortNavigationBar
import dev.aezochka.m3e.M3EShortNavigationItem

@Composable
internal fun NavigationComponentsScreen() {
    var segment by remember { mutableIntStateOf(0) }
    var destination by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Single-choice segmented buttons", style = MaterialTheme.typography.titleMedium)
        M3ESegmentedButtonRow(
            labels = listOf("Фото", "Видео", "Все"),
            selectedIndex = segment,
            onSelect = { segment = it },
        )
        Text("Выбрано: ${listOf("Фото", "Видео", "Все")[segment]}")
        Text("Short navigation bar", style = MaterialTheme.typography.titleMedium)
        M3EShortNavigationBar(
            items = listOf(
                M3EShortNavigationItem("Главная", Icons.Outlined.Home),
                M3EShortNavigationItem("Поиск", Icons.Outlined.Search),
                M3EShortNavigationItem("Настройки", Icons.Outlined.Settings),
            ),
            selectedIndex = destination,
            onSelect = { destination = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            "Нижняя expressive-навигация: выбранный индикатор и подписи.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp),
        )
    }
}
