package com.joseruiz.tictactoe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier


@Composable
fun JuegoScreen(jugador1: String, jugador2: String, size: Int, navController: NavController) {
    val tablero = remember { mutableStateListOf<List<String>>() }
    var turno by remember { mutableStateOf(if ((0..1).random() == 0) jugador1 else jugador2) }
    var ganador by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Turno de: $turno")
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(size)) {
            items(size * size) { index ->
                val row = index / size
                val col = index % size
                Button(onClick = {
                    if (tablero[row][col].isEmpty() && ganador.isEmpty()) {
                        tablero[row] = tablero[row].toMutableList().apply {
                            set(col, if (turno == jugador1) "X" else "O")
                        }
                        turno = if (turno == jugador1) jugador2 else jugador1
                        ganador = verificarGanador(tablero, size)
                        if (ganador.isNotEmpty()) {
                            navController.navigate("FinalActivity/$ganador")
                        }
                    }
                }) {
                    Text(text = tablero[row][col], style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

fun verificarGanador(tablero: List<List<String>>, size: Int): String {

    return ""
}