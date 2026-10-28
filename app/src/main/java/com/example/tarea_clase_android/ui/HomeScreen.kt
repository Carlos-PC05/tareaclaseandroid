package com.example.tarea_clase_android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarea_clase_android.ui.theme.FondoApp
import com.example.tarea_clase_android.ui.theme.MoradoBoton
import com.example.tarea_clase_android.ui.theme.TareaclaseandroidTheme

@Composable
fun HomeScreen(datos: DatosUsuario, onVolver: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoApp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 28.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = "Hola, ${datos.nombre.ifBlank { "invitado" }}",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.12f))
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Dato("Género", datos.genero)
                Dato("Montaña", datos.montana.toString())
                Dato("Sprint", datos.sprint.toString())
            }
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onVolver,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = MoradoBoton)
        ) {
            Text("Volver", color = Color.White, modifier = Modifier.padding(horizontal = 16.dp))
        }
        Spacer(Modifier.weight(1f))
        LogoUcam()
    }
}

@Composable
private fun Dato(etiqueta: String, valor: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(etiqueta, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
        Text(valor, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
private fun HomeScreenPreview() {
    TareaclaseandroidTheme {
        HomeScreen(
            datos = DatosUsuario("Carlos", "Hombre", 50, 50),
            onVolver = {}
        )
    }
}
