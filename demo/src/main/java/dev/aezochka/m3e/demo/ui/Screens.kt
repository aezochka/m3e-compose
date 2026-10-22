@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class,
)

package dev.aezochka.m3e.demo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import dev.aezochka.m3e.M3EButton
import dev.aezochka.m3e.M3EMaterial
import dev.aezochka.m3e.M3EMorphTile
import dev.aezochka.m3e.M3EOutlinedButton
import dev.aezochka.m3e.M3ETonalButton
import dev.aezochka.m3e.M3EButtonGroup
import dev.aezochka.m3e.M3EConnectedToggleGroup
import dev.aezochka.m3e.M3ESplitButton
import dev.aezochka.m3e.M3EToggleButton
import dev.aezochka.m3e.M3EFabMenu
import dev.aezochka.m3e.M3EFabMenuItem
import dev.aezochka.m3e.M3ELargeFlexibleTopAppBar
import dev.aezochka.m3e.M3EBottomAppBar
import dev.aezochka.m3e.M3EHorizontalFloatingToolbar
import dev.aezochka.m3e.M3EVerticalFloatingToolbar
import dev.aezochka.m3e.M3EWideNavigationRail
import dev.aezochka.m3e.M3EMultiBrowseCarousel
import dev.aezochka.m3e.M3ELoadingIndicator
import dev.aezochka.m3e.M3EContainedLoadingIndicator
import dev.aezochka.m3e.M3ELinearWavyProgress
import dev.aezochka.m3e.M3ECircularWavyProgress
import dev.aezochka.m3e.M3EWavyLinearLoading
import dev.aezochka.m3e.M3EWavyDivider
import dev.aezochka.m3e.M3EMenuGroup
import dev.aezochka.m3e.M3EMenuItem
import dev.aezochka.m3e.M3ECheckableMenuItem
import dev.aezochka.m3e.M3EMotion
import dev.aezochka.m3e.M3ESegmentedButtonRow
import dev.aezochka.m3e.M3EShortNavigationBar
import dev.aezochka.m3e.M3EShortNavigationItem
import dev.aezochka.m3e.m3ePopIn
import dev.aezochka.m3e.m3ePress

@Composable
fun SectionScreen(key: String, back: () -> Unit) {
    val title = sections.firstOrNull { it.key == key }?.title ?: "M3E"
    val scrollBehavior = if (key == "appbar") TopAppBarDefaults.enterAlwaysScrollBehavior() else null
    Scaffold(
        modifier = if (scrollBehavior != null) {
            Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        } else Modifier,
        topBar = {
            if (key == "appbar") {
                M3ELargeFlexibleTopAppBar(
                    title = { Text(title) },
                    navigationIcon = { IconButton(onClick = back) { Icon(Icons.Filled.Close, "Назад") } },
                    scrollBehavior = scrollBehavior,
                )
            } else {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = { IconButton(onClick = back) { Icon(Icons.Filled.Close, "Назад") } },
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (key) {
                "buttons" -> ButtonsScreen()
                "toggle" -> ToggleScreen()
                "buttongroup" -> ButtonGroupScreen()
                "split" -> SplitScreen()
                "toolbar" -> ToolbarScreen()
                "fabmenu" -> FabMenuScreen(back)
                "appbar" -> AppBarScreen()
                "bottombar" -> BottomBarScreen()
                "rail" -> RailScreen()
                "navcomponents" -> NavigationComponentsScreen()
                "carousel" -> CarouselScreen()
                "progress" -> ProgressScreen()
                "sheets" -> SheetsScreen()
                "menus" -> MenusScreen()
                "morph" -> MorphScreen()
                "containers" -> ContainersScreen()
                "inputs" -> InputsScreen()
                "search" -> SearchScreen()
                "time" -> TimeScreen()
                "lists" -> ListsScreen()
                "press" -> PressScreen()
                "sizes" -> ButtonSizesScreen()
                "iconbuttons" -> IconButtonsScreen()
                "fabs" -> FabsScreen()
                "sliders" -> SlidersScreen()
                "actions" -> ActionsScreen()
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ButtonsScreen() {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        M3EButton(onClick = {}) { Text("Filled") }
        M3ETonalButton(onClick = {}) { Text("Tonal") }
        M3EOutlinedButton(onClick = {}) { Text("Outlined") }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Button(onClick = {}) { Text("Plain m3") }
        M3EButton(onClick = {}, enabled = false) { Text("Disabled") }
    }
}

@Composable
private fun ToggleScreen() {
    var on by remember { mutableStateOf(true) }
    M3EToggleButton(checked = on, onCheckedChange = { on = it }) { Text(if (on) "Вкл" else "Выкл") }
    var sel by remember { mutableStateOf(0) }
    M3EConnectedToggleGroup(
        options = listOf("Работа", "Еда", "Кофе"),
        selected = sel,
        onSelect = { sel = it },
    )
    var checked by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Switch(checked = checked, onCheckedChange = { checked = it })
        Text("Switch (expressive спринги)")
    }
}

@Composable
private fun ButtonGroupScreen() {
    M3EButtonGroup {
        repeat(12) { i ->
            clickableItem(onClick = {}, label = "Действие $i")
        }
    }
}

@Composable
private fun SplitScreen() {
    M3ESplitButton(text="Поделиться", onClick = {}, leadingIcon = Icons.Outlined.Share)
    M3ESplitButton(text="Сохранить", onClick = {}, leadingIcon = Icons.Outlined.Edit, expanded = true)
}

@Composable
private fun ToolbarScreen() {
    var expanded by remember { mutableStateOf(false) }
    M3EHorizontalFloatingToolbar(expanded = expanded) {
        IconButton(onClick = { expanded = !expanded }) { Icon(Icons.Outlined.Search, "Поиск") }
        IconButton(onClick = {}) { Icon(Icons.Outlined.Home, "Дом") }
        IconButton(onClick = {}) { Icon(Icons.Outlined.Settings, "Настройки") }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        M3EVerticalFloatingToolbar(expanded = expanded) {
            IconButton(onClick = { expanded = !expanded }) { Icon(Icons.Outlined.Coffee, "Кофе") }
            IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, "Нравится") }
        }
        Text("Тумблер — в самой пилюле", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun FabMenuScreen(back: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    BackHandler(enabled = expanded) { expanded = false }
    Box(Modifier.fillMaxWidth().height(280.dp)) {
        M3EFabMenu(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            items = listOf(
                M3EFabMenuItem(Icons.Outlined.Share, "Поделиться"),
                M3EFabMenuItem(Icons.Outlined.Edit, "Изменить"),
                M3EFabMenuItem(Icons.Filled.Favorite, "В избранное"),
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun AppBarScreen() {
    repeat(30) {
        Text(
            "Строка $it — скролль, чтобы увидеть, как flexible app bar схлопывается",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        )
    }
}

@Composable
private fun BottomBarScreen() {
    Box(Modifier.fillMaxWidth().height(200.dp)) {
        Text(
            "BottomAppBar с FAB — снизу. FAB пружинит при нажатии.",
            style = MaterialTheme.typography.bodyMedium,
        )
        Column(Modifier.align(Alignment.BottomCenter)) {
            var clicked by remember { mutableStateOf(false) }
            val scale by animateFloatAsState(
                targetValue = if (clicked) 1.15f else 1f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
                label = "fab",
            )
            M3EBottomAppBar(
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Filled.Home, "Дом") }
                    IconButton(onClick = {}) { Icon(Icons.Filled.Search, "Поиск") }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { clicked = !clicked }) {
                        Icon(Icons.Filled.Add, "Добавить", modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale })
                    }
                },
            )
        }
    }
}

@Composable
private fun RailScreen() {
    var sel by remember { mutableStateOf(0) }
    M3EWideNavigationRail(
        items = listOf(
            Icons.Outlined.Home to "Дом",
            Icons.Outlined.Search to "Поиск",
            Icons.Outlined.Settings to "Настройки",
        ),
        selected = sel,
        onSelect = { sel = it },
    )
}

@Composable
private fun CarouselScreen() {
    val scheme = MaterialTheme.colorScheme
    M3EMultiBrowseCarousel(itemCount = 12) { index ->
        Box(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(if (index % 2 == 0) scheme.primaryContainer else scheme.tertiaryContainer),
            contentAlignment = Alignment.Center,
        ) { Text("Карточка $index", color = scheme.onPrimaryContainer) }
    }
}

@Composable
private fun ProgressScreen() {
    var p by remember { mutableFloatStateOf(0.6f) }
    M3ELinearWavyProgress(progress = { p })
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
        M3ELoadingIndicator()
        M3EContainedLoadingIndicator()
        M3ECircularWavyProgress(progress = { p })
    }
    M3EWavyLinearLoading()
    M3EWavyDivider(progress = 0.8f)
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        M3EButton(onClick = { p = ((p + 0.15f) % 1.001f).coerceIn(0f, 1f) }) { Text("+") }
        M3ETonalButton(onClick = { p = (p - 0.15f).coerceAtLeast(0f) }) { Text("−") }
    }
}

@Composable
private fun SheetsScreen() {
    var modal by remember { mutableStateOf(false) }
    M3EButton(onClick = { modal = true }) { Text("Открыть шторку") }
    if (modal) {
        dev.aezochka.m3e.M3ESheet(onDismissRequest = { modal = false }) {
            Text("Обычная expressive-шторка", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun MenusScreen() {
    var checked by remember { mutableStateOf(true) }
    M3EMenuGroup {
        M3EMenuItem("Поделиться", onClick = {}, leadingIcon = Icons.Outlined.Share)
        M3EMenuItem("Изменить", onClick = {}, leadingIcon = Icons.Outlined.Edit)
        M3ECheckableMenuItem("Уведомления", checked = checked, onCheckedChange = { checked = it })
    }
}

@Composable
private fun MorphScreen() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        M3EMorphTile("Pill")
        M3EMorphTile("Cookie", idle = androidx.compose.material3.MaterialShapes.Cookie9Sided, pressed = androidx.compose.material3.MaterialShapes.Pill)
    }
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        M3EMorphTile("Clover", idle = androidx.compose.material3.MaterialShapes.Clover4Leaf, pressed = androidx.compose.material3.MaterialShapes.Sunny)
        M3EMorphTile("Burst", idle = androidx.compose.material3.MaterialShapes.SoftBurst, pressed = androidx.compose.material3.MaterialShapes.Pill)
    }
    Text("Держи палец — фигура пружинно морфится в другую", style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun PressScreen() {
    val scheme = MaterialTheme.colorScheme
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        repeat(4) { i ->
            Box(
                modifier = Modifier
                    .m3ePopIn(index = i)
                    .m3ePress(onClick = {})
                    .background(scheme.surfaceContainerHigh)
                    .size(72.dp),
                contentAlignment = Alignment.Center,
            ) { Text("$i") }
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            Modifier
                .m3ePress(onClick = {})
                .background(scheme.primary)
                .size(64.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {}
    }
    M3EWavyDivider(progress = 1f)
}
