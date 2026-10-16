@file:OptIn(androidx.compose.material3.ExperimentalMaterial3ExpressiveApi::class)

package dev.aezochka.m3e

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Полный M3E-базис для любого приложения Compose, в один вызов:
 *
 * - [MaterialExpressiveTheme] + MotionScheme.expressive() — пружинные дефолты
 *   у стандартных компонентов material3;
 * - [m3eShapes] — экспрессивная шкала углов (10/14/18/26/34);
 * - Material You динамические цвета (Android 12+), с фолбэком на базовые схемы;
 * - своя схема (например, как в buds-control) — через [colorScheme].
 *
 * Это то, чего нет «из коробки» в material3 1.5.0-beta01: тема с expressive-спрингами
 * есть, но углы и press-морфизмы библиотека не применяет сама — применяет [M3ETheme]
 * и модификаторы [m3ePress]/[m3ePopIn].
 */
@Composable
fun M3ETheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    colorScheme: ColorScheme? = null,
    shapes: Shapes = m3eShapes(),
    typography: Typography = Typography(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val scheme = colorScheme ?: when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }
    MaterialExpressiveTheme(
        colorScheme = scheme,
        motionScheme = MotionScheme.expressive(),
        shapes = shapes,
        typography = typography,
        content = content,
    )
}
