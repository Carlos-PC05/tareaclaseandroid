package com.example.tarea_clase_android.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarea_clase_android.ui.theme.Amarillo
import com.example.tarea_clase_android.ui.theme.AzulLogo
import com.example.tarea_clase_android.ui.theme.FondoApp
import com.example.tarea_clase_android.ui.theme.GrisCampo
import com.example.tarea_clase_android.ui.theme.MoradoBoton
import com.example.tarea_clase_android.ui.theme.Rojo
import com.example.tarea_clase_android.ui.theme.TareaclaseandroidTheme
import kotlin.math.roundToInt

private const val PASO = 10
private val OPCIONES_GENERO = listOf("Hombre", "Mujer", "Otro")

@Composable
fun LoginScreen(onEnviar: (DatosUsuario) -> Unit, modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf(OPCIONES_GENERO.first()) }
    var montana by rememberSaveable { mutableIntStateOf(50) }
    var sprint by rememberSaveable { mutableIntStateOf(50) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoApp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoPrincipal()
        Spacer(Modifier.height(44.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("Ingrese su nombre", fontSize = 14.sp) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisCampo,
                unfocusedContainerColor = GrisCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color(0xFF333333),
                unfocusedPlaceholderColor = Color(0xFF333333)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(36.dp))
        SelectorGenero(genero) { genero = it }
        Spacer(Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                SliderPunteado(montana) { montana = it }
                Text("Montaña: $montana", color = Color.White, fontSize = 13.sp)
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                SliderPunteado(sprint) { sprint = it }
                Text("Sprint: $sprint", color = Color.White, fontSize = 13.sp)
            }
        }
        Spacer(Modifier.height(28.dp))
        Button(
            onClick = { onEnviar(DatosUsuario(nombre.trim(), genero, montana, sprint)) },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = MoradoBoton)
        ) {
            Text("Enviar", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 16.dp))
        }
        Spacer(Modifier.height(40.dp))
        LogoUcam()
    }
}

// Placeholder: sustituir por el logo real en res/drawable cuando esté disponible.
@Composable
private fun LogoPrincipal() {
    Box(
        modifier = Modifier
            .size(180.dp)
            .background(Amarillo)
    ) {
        Text(
            text = "P",
            color = Rojo,
            fontSize = 190.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.offset(x = 6.dp, y = (-40).dp)
        )
        Text(
            text = "ucam",
            color = AzulLogo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 8.dp, bottom = 6.dp)
        )
    }
}

@Composable
internal fun LogoUcam() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "UCAM",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = "UNIVERSIDAD CATÓLICA DE MURCIA",
            color = Color.White,
            fontSize = 9.sp,
            letterSpacing = 1.sp
        )
    }
}

@Composable
private fun SelectorGenero(genero: String, onGeneroChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Género:", color = Color.White, fontSize = 14.sp)
        OPCIONES_GENERO.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == opcion,
                    onClick = { onGeneroChange(opcion) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Amarillo,
                        unselectedColor = Color.White
                    )
                )
                Text(opcion, color = Color.White, fontSize = 13.sp)
            }
        }
    }
}

/** Slider de puntos: se puede tocar o arrastrar y siempre cae en múltiplos de [PASO]. */
@Composable
private fun SliderPunteado(valor: Int, modifier: Modifier = Modifier, onValorChange: (Int) -> Unit) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .pointerInput(Unit) {
                awaitEachGesture {
                    fun actualizar(x: Float) {
                        val fraccion = (x / size.width).coerceIn(0f, 1f)
                        onValorChange((fraccion * (100 / PASO)).roundToInt() * PASO)
                    }
                    actualizar(awaitFirstDown().position.x)
                    var presionado = true
                    while (presionado) {
                        val evento = awaitPointerEvent()
                        evento.changes.firstOrNull()?.let { actualizar(it.position.x) }
                        presionado = evento.changes.any { it.pressed }
                    }
                }
            }
    ) {
        val margen = 6.dp.toPx()
        val ancho = size.width - 2 * margen
        val y = size.height / 2
        drawLine(
            color = Color.White.copy(alpha = 0.5f),
            start = Offset(margen, y),
            end = Offset(size.width - margen, y),
            strokeWidth = 3f,
            cap = StrokeCap.Round
        )
        for (i in 0..(100 / PASO)) {
            val x = margen + ancho * i / (100 / PASO)
            val seleccionado = i * PASO == valor
            drawCircle(
                color = if (i * PASO <= valor) Color.White else Color.White.copy(alpha = 0.5f),
                radius = if (seleccionado) 6.dp.toPx() else 4.dp.toPx(),
                center = Offset(x, y)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
private fun LoginScreenPreview() {
    TareaclaseandroidTheme {
        LoginScreen(onEnviar = {})
    }
}
