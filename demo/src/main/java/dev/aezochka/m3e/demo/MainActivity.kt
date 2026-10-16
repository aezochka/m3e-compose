package dev.aezochka.m3e.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.aezochka.m3e.M3ETheme
import dev.aezochka.m3e.demo.ui.Catalog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M3ETheme {
                Catalog()
            }
        }
    }
}
