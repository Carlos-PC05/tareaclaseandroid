package com.example.tarea_clase_android.ui

/** Datos que viajan desde la pantalla de login hasta la pantalla Home. */
data class DatosUsuario(
    val nombre: String,
    val genero: String,
    val montana: Int,
    val sprint: Int
)
