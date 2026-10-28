package com.example.tarea_clase_android

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tarea_clase_android.ui.DatosUsuario
import com.example.tarea_clase_android.ui.HomeScreen
import com.example.tarea_clase_android.ui.LoginScreen
import com.example.tarea_clase_android.ui.theme.TareaclaseandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.rgb(255, 248, 255), Color.rgb(255, 248, 255)),
            navigationBarStyle = SystemBarStyle.light(Color.rgb(255, 248, 255), Color.rgb(255, 248, 255))
        )
        setContent {
            TareaclaseandroidTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    // shortcut: estado en memoria; al rotar la pantalla se vuelve al login
    var datos by remember { mutableStateOf<DatosUsuario?>(null) }
    val actual = datos
    if (actual == null) {
        LoginScreen(onEnviar = { datos = it })
    } else {
        BackHandler { datos = null }
        HomeScreen(datos = actual, onVolver = { datos = null })
    }
}
