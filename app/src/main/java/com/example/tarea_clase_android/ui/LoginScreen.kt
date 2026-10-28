package com.example.tarea_clase_android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import com.example.tarea_clase_android.R
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarea_clase_android.ui.theme.FondoApp
import com.example.tarea_clase_android.ui.theme.GrisCampo
import com.example.tarea_clase_android.ui.theme.MoradoBoton
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
            .background(Color(0xFFFFF8FF))
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(FondoApp)
            .verticalScroll(rememberScrollState())
            .padding(top = 56.dp, bottom = 42.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoPrincipal()
        Spacer(Modifier.height(48.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("Ingrese su nombre", fontSize = 16.sp) },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            shape = RoundedCornerShape(2.dp),
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
            modifier = Modifier.width(296.dp).height(72.dp)
                .border(8.dp, Color(0xFF888888), RoundedCornerShape(9.dp))
                .padding(8.dp)
        )
        Spacer(Modifier.height(24.dp))
        SelectorGenero(genero) { genero = it }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                SliderPunteado(montana) { montana = it }
                Text("Montaña: $montana", color = Color.White, fontSize = 16.sp)
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                SliderPunteado(sprint) { sprint = it }
                Text("Sprint: $sprint", color = Color.White, fontSize = 16.sp)
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { onEnviar(DatosUsuario(nombre.trim(), genero, montana, sprint)) },
            modifier = Modifier.size(88.dp, 40.dp),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = MoradoBoton)
        ) {
            Text("Enviar", color = Color.White, fontSize = 14.sp)
        }
        Spacer(Modifier.height(82.dp))
        LogoUcam()
    }
}

@Composable
private fun LogoPrincipal() {
    Box(Modifier.size(200.dp).background(Color.Yellow)) {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Color.Red, size = Size(size.width * .75f, size.height * .75f))
            drawRect(Color.Red, topLeft = Offset(0f, size.height * .75f),
                size = Size(size.width * .25f, size.height * .25f))
            drawRect(Color.Yellow, topLeft = Offset(size.width * .25f, size.height * .25f),
                size = Size(size.width * .25f, size.height * .25f))
        }
        Text("DM UCAM", color = Color.Blue, fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 36.dp, bottom = 4.dp))
    }
}

@Composable
internal fun LogoUcam() {
    Image(painterResource(R.drawable.ucam_logo),
        contentDescription = "UCAM, Universidad Católica de Murcia",
        modifier = Modifier.size(206.dp, 88.dp))
}

@Composable
private fun SelectorGenero(genero: String, onGeneroChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Género:", color = Color.White, fontSize = 18.sp)
        OPCIONES_GENERO.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == opcion,
                    onClick = { onGeneroChange(opcion) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.White,
                        unselectedColor = Color.White
                    )
                )
                Text(opcion, color = Color.White, fontSize = 12.sp)
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
            .height(44.dp)
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
        val y = size.height / 2
        val thumb = size.width * valor / 100f
        val track = 16.dp.toPx()
        drawRoundRect(MoradoBoton, topLeft = Offset(0f, y - track / 2),
            size = Size(size.width, track), cornerRadius = androidx.compose.ui.geometry.CornerRadius(track / 2))
        drawRoundRect(GrisCampo, topLeft = Offset(thumb, y - track / 2),
            size = Size(size.width - thumb, track), cornerRadius = androidx.compose.ui.geometry.CornerRadius(track / 2))
        for (i in 0..10) {
            val x = 7.dp.toPx() + (size.width - 14.dp.toPx()) * i / 10
            drawCircle(if (x < thumb) GrisCampo else MoradoBoton, 2.dp.toPx(), Offset(x, y))
        }
        drawLine(MoradoBoton, Offset(thumb, 0f), Offset(thumb, size.height), 4.dp.toPx())
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
private fun LoginScreenPreview() {
    TareaclaseandroidTheme {
        LoginScreen(onEnviar = {})
    }
}
