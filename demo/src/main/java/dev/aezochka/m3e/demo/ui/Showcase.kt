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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.M3EButton
import dev.aezochka.m3e.M3ELinearWavyProgress
import dev.aezochka.m3e.M3ELoadingIndicator
import dev.aezochka.m3e.M3EOutlinedButton
import dev.aezochka.m3e.M3ESheet
import dev.aezochka.m3e.M3ETonalButton
import dev.aezochka.m3e.M3EWavyDivider
import dev.aezochka.m3e.M3EMaterial
import dev.aezochka.m3e.m3ePopIn
import dev.aezochka.m3e.m3ePress
import dev.aezochka.m3e.m3ePillPress

@Composable
fun Showcase() {
    val scheme = MaterialTheme.colorScheme
    Scaffold(
        topBar = { TopAppBar(title = { Text("M3E Compose") }) },
        containerColor = scheme.surface,
    ) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Section("Кнопки: морф pill → сжатый угол")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                M3EButton(onClick = {}) { Text("Filled") }
                M3ETonalButton(onClick = {}) { Text("Tonal") }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                M3EOutlinedButton(onClick = {}) { Text("Outlined") }
                Button(onClick = {}) { Text("Plain m3") }
            }

            Section("Плитки: пружинный press + морф угла, поп-ин волной")
            val tiles = listOf("A", "B", "C", "D", "E", "F", "G", "H")
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth().height(220.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(tiles) { t ->
                    Box(
                        modifier = Modifier
                            .m3ePopIn(index = tiles.indexOf(t))
                            .m3ePress(onClick = {}, corner = 10.dp, cornerPressed = 26.dp)
                            .background(scheme.surfaceContainerHigh)
                            .size(64.dp),
                        contentAlignment = Alignment.Center,
                    ) { Text(t) }
                }
            }

            Section("Wavy-индикаторы")
            var progress by remember { mutableFloatStateOf(0.65f) }
            M3ELinearWavyProgress(progress = { progress })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                M3ELoadingIndicator()
                Text("loading…", style = MaterialTheme.typography.bodyMedium)
            }
            M3EWavyDivider(progress = 0.8f)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                M3EButton(onClick = { progress = ((progress + 0.1f) % 1.01f).coerceIn(0f, 1f) }) {
                    Text("+")
                }
                M3ETonalButton(onClick = { progress = (progress - 0.1f).coerceAtLeast(0f) }) {
                    Text("−")
                }
            }

            Section("Материальные формы")
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    Modifier.size(64.dp).clip(M3EMaterial.pill())
                        .background(scheme.primaryContainer),
                    contentAlignment = Alignment.Center,
                ) { Text("Pill", color = scheme.onPrimaryContainer) }
                Box(
                    Modifier.size(64.dp).clip(M3EMaterial.cookie())
                        .background(scheme.tertiaryContainer),
                    contentAlignment = Alignment.Center,
                ) { Text("Cookie", color = scheme.onTertiaryContainer) }
                Box(
                    Modifier.size(64.dp).clip(M3EMaterial.clover())
                        .background(scheme.secondaryContainer),
                    contentAlignment = Alignment.Center,
                ) { Text("Clover", color = scheme.onSecondaryContainer) }
            }

            Section("Шторка: 28dp + пружины")
            var sheet by remember { mutableStateOf(false) }
            M3EButton(onClick = { sheet = true }) { Text("Открыть шторку") }
            if (sheet) {
                M3ESheet(onDismissRequest = { sheet = false }) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Expressive sheet", style = MaterialTheme.typography.titleLarge)
                        Text(
                            "Крупные углы, пружинные детенты, Material You цвета.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = scheme.onSurfaceVariant,
                        )
                        M3EButton(onClick = { sheet = false }) { Text("Закрыть") }
                    }
                }
            }

            Section("Пилюли-иконки (pillPress)")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IconButton(onClick = {}) { Icon(Icons.Outlined.Favorite, null) }
                Box(
                    Modifier
                        .m3ePillPress(onClick = {})
                        .clip(RoundedCornerShape(percent = 50))
                        .background(scheme.primary)
                        .padding(horizontal = 18.dp, vertical = 10.dp),
                ) { Text("Pill", color = scheme.onPrimary) }
                Box(
                    Modifier
                        .m3ePillPress(onClick = {})
                        .clip(M3EMaterial.pill())
                        .background(scheme.surfaceContainerHighest)
                        .padding(horizontal = 18.dp, vertical = 10.dp),
                ) { Text("Material") }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Section(title: String) {
    Column {
        Spacer(Modifier.height(4.dp))
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
}
