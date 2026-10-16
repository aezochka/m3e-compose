@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e.demo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material.icons.outlined.SmartButton
import androidx.compose.material.icons.outlined.CallSplit
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.ToggleOn
import androidx.compose.material.icons.outlined.TripOrigin
import androidx.compose.material.icons.outlined.ViewSidebar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.m3ePopIn
import dev.aezochka.m3e.m3ePress

/** Секция каталога. */
data class Section(
    val key: String,
    val title: String,
    val icon: ImageVector,
)

val sections = listOf(
    Section("buttons", "Кнопки-морфы", Icons.Outlined.SmartButton),
    Section("toggle", "Toggle + connected", Icons.Outlined.ToggleOn),
    Section("buttongroup", "ButtonGroup", Icons.Outlined.GridView),
    Section("split", "SplitButton", Icons.Outlined.CallSplit),
    Section("toolbar", "FloatingToolbar", Icons.Outlined.Tune),
    Section("fabmenu", "FAB-меню", Icons.Outlined.Bolt),
    Section("appbar", "Flexible app bar", Icons.Outlined.Dashboard),
    Section("bottombar", "BottomAppBar + FAB", Icons.Outlined.Category),
    Section("rail", "Wide NavigationRail", Icons.Outlined.ViewSidebar),
    Section("carousel", "Carousel", Icons.Outlined.PictureAsPdf),
    Section("progress", "Wavy / Loading", Icons.Outlined.TripOrigin),
    Section("sheets", "Sheets", Icons.Outlined.Layers),
    Section("menus", "Меню-группы", Icons.Outlined.List),
    Section("morph", "Морфы фигур", Icons.Outlined.Extension),
    Section("press", "Press / Pop-in / Волна", Icons.Outlined.TouchApp),
)

@Composable
fun Catalog() {
    var route by remember { mutableStateOf<String?>(null) }
    val current = route
    if (current == null) {
        Home { route = it }
    } else {
        SectionScreen(current) { route = null }
    }
}

@Composable
private fun Home(open: (String) -> Unit) {
    val scheme = MaterialTheme.colorScheme
    Scaffold(
        topBar = { TopAppBar(title = { Text("M3E Compose — каталог") }) },
        containerColor = scheme.surface,
    ) { pad ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            modifier = Modifier.fillMaxSize().padding(pad),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(sections) { s ->
                Box(
                    modifier = Modifier
                        .m3ePopIn(index = sections.indexOf(s) % 8)
                        .m3ePress(onClick = { open(s.key) })
                        .background(scheme.surfaceContainerHigh)
                        .fillMaxWidth()
                        .height(96.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(s.icon, contentDescription = s.title, tint = scheme.primary)
                        Text(
                            s.title,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                }
            }
        }
    }
}
