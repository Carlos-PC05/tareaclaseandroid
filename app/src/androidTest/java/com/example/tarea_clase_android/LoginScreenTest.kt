package com.example.tarea_clase_android

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun enviarConservaLosDatosDelFormulario() {
        compose.onNodeWithText("Montaña: 50").assertExists()
        compose.onNodeWithText("Sprint: 50").assertExists()
        compose.onNodeWithText("Ingrese su nombre").performTextInput("Ana")
        compose.onNodeWithText("Enviar").performClick()
        compose.onNodeWithText("Hola, Ana").assertExists()
        compose.onNodeWithText("Hombre").assertExists()
    }
}
