# m3e-compose

Общий порт Material 3 Expressive для Android-приложений (Glimpse, buds-control, …).
Поверх `androidx.compose.material3:material3:1.5.0-beta02`, которая сама по себе —
не полный порт: компоненты есть, но дефолтов нет.

Сверялся с живыми M3E-проектами: meticha/material-3-expressive-catalog,
ocegik/Material3Expressive, M3E Canvas (setproduct).

## Что внутри (v0.5.0)

**Тема и движение**
- `M3ETheme` — MaterialExpressiveTheme + MotionScheme.expressive() + экспрессивная
  шкала углов (10/14/18/26/34) + Material You динамические цвета, в один вызов.
- `M3EMotion` — общий словарь спринов: мягкие spatial (0.82) / spatialFast (0.86), `bouncy()` — старый овершут, effects.
- `Modifier.m3ePress` — фирменный пружинный press: спад + морф угла.
- `Modifier.m3ePopIn` — стэггерное волной-появление.
- `M3EMaterial` — MaterialShapes (Pill / Cookie9Sided / Clover4Leaf / Sunny) как Shape.

**Кнопки**
- `M3EButton` / `M3ETonalButton` / `M3EOutlinedButton` — морф pill → сжатый угол.
- `M3EToggleButton` — idle → pressed → checked(пилюля) shape-морф.
- `M3EConnectedToggleGroup` — connected-группа (радио-стиль M3E).
- `M3EButtonGroup` — строка кнопок с автоматическим overflow-меню.
- `M3ESplitButton` — split с проворачивающейся стрелкой (controlled через `expanded`).
- `M3EButtonSize` — пять размеров XS/S/M/L/XL (высота, паддинги, шрифт, pressed-форма из токенов)
  для всех кнопок; + `M3EElevatedButton`, `M3ETextButton`.
- `M3EIconButton` / `M3EIconToggleButton` — 4 заливки × 5 размеров × 3 ширины (narrow/uniform/wide),
  круг → квадрат при checked.
- `M3EFab` / `M3EExtendedFab` — regular/medium/large, extended схлопывается по `expanded`.

**Ввод и действия**
- `M3ESlider` / `M3EVerticalSlider` — новый толстый трек на state-API.
- `M3ESwitch` — с галочкой в ручке.
- `M3EAppBarRow` — действия с авто-overflow в «⋮» (`AppBarRow`).
- `M3ETooltip` — PlainTooltip по долгому нажатию.

**Поверхности и навигация**
- `M3ELargeFlexibleTopAppBar` / `M3EMediumFlexibleTopAppBar` — flexible-схлопывание.
- `M3EBottomAppBar` — с FAB-слотом.
- `M3EHorizontalFloatingToolbar` / `M3EVerticalFloatingToolbar` — пилюли-тулбары.
- `M3EFabMenu` — FAB-меню (ToggleFloatingActionButton + выезд пунктов).
- `M3EWideNavigationRail` — разворачивающийся рельс.
- `M3ESegmentedButtonRow` — single-choice segmented control с материал-формами для краёв.
- `M3EShortNavigationBar` — expressive нижняя навигация с выбранным индикатором.
- `M3EMultiBrowseCarousel` — carousel (multi-browse).
- `M3ESheet` / `M3EFlexibleSheet` — шторки; flexible с expandedContent.
- `M3EMenuGroup` / `M3EMenuItem` / `M3ECheckableMenuItem` — меню-«острова».

**Индикаторы**
- `M3ELoadingIndicator` / `M3EContainedLoadingIndicator` — пульсирующие волны.
- `M3ELinearWavyProgress` / `M3ECircularWavyProgress` — wavy-прогресс.
- `M3EWavyLinearLoading` / `M3EWavyCircularLoading` — недетерминированные.
- `M3EWavyDivider` — волна-разделитель из прототипа m3e.

**Морфы фигур (Morph)**
- `M3EMorphShape` / `m3eMorphShape` — интерполяция двух MaterialShapes-полигонов.
- `M3EMorphTile` — плитка, морфящаяся pill → cookie под пальцем.

## Подключение (GitHub Packages)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        google(); mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/aezochka/m3e-compose")
            credentials {
                username = "aezochka"
                password = "<PAT с read:packages>"
            }
        }
    }
}

// app
implementation("dev.aezochka:m3e-compose:0.5.0")
```

В GitHub Actions вместо PAT — `secrets.GITHUB_TOKEN` (+ `permissions: packages: read`)
через env `ORG_GRADLE_PROJECT_gprUser` / `ORG_GRADLE_PROJECT_gprToken`.

## Модули

- `:m3e` — библиотека (AAR, публикуется в GitHub Packages по тегу `v*`).
- `:demo` — витрина всех компонентов (CI собирает APK).

## Сборка

```
./gradlew :demo:assembleDebug
```

JDK 17, Android SDK 37.
