@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e.demo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.M3EBadge
import dev.aezochka.m3e.M3EBadgedBox
import dev.aezochka.m3e.M3ECard
import dev.aezochka.m3e.M3EContainer
import dev.aezochka.m3e.M3EContainerLevel
import dev.aezochka.m3e.M3EDivider
import dev.aezochka.m3e.M3EListItem
import dev.aezochka.m3e.M3EOutlinedTextField
import dev.aezochka.m3e.M3ESearchBar
import dev.aezochka.m3e.M3ETimePickerField
import dev.aezochka.m3e.M3EShape

@Composable
internal fun ContainersScreen() {
    // 5 уровней контейнера — тона для иерархии контента
    M3EContainerLevel.entries.forEach { level ->
        M3EContainer(level = level, modifier = Modifier.fillMaxWidth()) {
            Text(
                "Контейнер ${level.name}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        M3ECard(onClick = {}, modifier = Modifier.weight(1f)) {
            Text("Карточка 28dp", modifier = Modifier.padding(16.dp))
        }
        M3EContainer(onClick = {}, modifier = Modifier.weight(1f), level = M3EContainerLevel.HIGH) {
            Text("Нажми — морф угла", modifier = Modifier.padding(16.dp))
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        M3EBadgedBox(badge = { M3EBadge(count = "7") }) {
            Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(28.dp))
        }
        M3EBadgedBox(badge = { M3EBadge() }) {
            Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(28.dp))
        }
    }
    M3EDivider()
    // библиотека форм: витрина
    LazyVerticalGrid(
        columns = GridCells.Adaptive(72.dp),
        modifier = Modifier.fillMaxWidth().height(240.dp).padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(M3EShape.entries) { s ->
            val scheme = MaterialTheme.colorScheme
            Box(
                Modifier.size(64.dp)
                    .clip(s.shape())
                    .background(scheme.tertiaryContainer),
            )
        }
    }
}

@Composable
internal fun InputsScreen() {
    var text by remember { mutableStateOf("") }
    M3EOutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Имя") },
        placeholder = { Text("Как звать?") },
        modifier = Modifier.fillMaxWidth(),
    )
    M3EOutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Только цифры") },
        isError = true,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
internal fun SearchScreen() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    M3ESearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(4) { i ->
            M3EListItem(
                headline = "Результат $i",
                supporting = "подзаголовок для $i",
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
internal fun TimeScreen() {
    M3ETimePickerField(modifier = Modifier.fillMaxWidth())
    Text(
        "Vibrant TimePicker: цветные контейнеры цифр, пилюля-переключатель клавиатура/циферблат",
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
internal fun ListsScreen() {
    var selected by remember { mutableStateOf(0) }
    repeat(6) { i ->
        M3EListItem(
            headline = "Пункт $i",
            supporting = "выбранный — пилюля, нажатый — 28dp",
            selected = selected == i,
            onClick = { selected = i },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
