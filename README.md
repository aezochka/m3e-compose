# m3e-compose

Общий порт Material 3 Expressive для Android-приложений (Glimpse, buds-control, …).
Поверх `androidx.compose.material3:material3:1.5.0-beta01`, которая сама по себе —
не полный порт: компоненты есть, но дефолтов нет.

## Что добавляет к material3 1.5.0-beta01

- `M3ETheme` — MaterialExpressiveTheme + MotionScheme.expressive() + экспрессивная
  шкала углов (10/14/18/26/34) + Material You динамические цвета, в один вызов.
- `Modifier.m3ePress` — фирменный пружинный press: спад масштаба + морф угла
  (прототипная физика cubic-bezier(.34,1.45,.5,1) ≈ spring(0.62, MediumLow)).
- `Modifier.m3ePopIn` — стэггерное волной-появление элементов.
- `M3EButton` / `M3ETonalButton` / `M3EOutlinedButton` — кнопки, которые морфятся
  pill → сжатый угол при нажатии (в beta01 Button умеет `ButtonShapes`, но не
  применяет их по умолчанию).
- `M3EWavyDivider` — волна-разделитель из прототипа m3e.
- `M3ELinearWavyProgress` / `M3ELoadingIndicator` — wavy-индикаторы с тематическими
  дефолтами.
- `M3ESheet` — expressive-шторка (28dp углы, пружинные детенты).
- `M3EMaterial` — MaterialShapes (Pill / Cookie9Sided / Clover4Sided) готовыми Shape.
- `M3EMotion` — общий словарь спринов (spatial / spatialFast / effects).

Чего в beta01 нет вообще и что пока не здесь: Carousel, FlexibleBottomSheet
с expandedContent, ButtonGroup/SplitButton/FloatingToolbar-обёртки — компоненты
Google в beta01 уже есть, при надобности оборачиваются поверх.

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
implementation("dev.aezochka:m3e-compose:0.1.0")
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
