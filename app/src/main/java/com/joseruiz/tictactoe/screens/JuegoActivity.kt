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
    val tablero = remember { mutableStateListOf<List<String>>().apply {
        repeat(size) { add(List(size) { "" }) }
    } }
    var turno by remember { mutableStateOf(if ((0..1).random() == 0) jugador1 else jugador2) }
    var ganador by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Turno de: $turno")
        Spacer(modifier = Modifier.height(20.dp))

        // Se construye la matriz seleccionada
        LazyVerticalGrid(columns = GridCells.Fixed(size)) {
            items(size * size) { index ->
                val row = index / size
                val col = index % size
                Button(
                    onClick = {
                        if (tablero[row][col].isEmpty() && ganador.isEmpty()) {
                            tablero[row] = tablero[row].toMutableList().apply {
                                set(col, if (turno == jugador1) "X" else "O")
                            }
                            turno = if (turno == jugador1) jugador2 else jugador1
                            ganador = verificarGanador(tablero, size, jugador1, jugador2)
                            if (ganador.isNotEmpty()) {
                                navController.navigate("FinalActivity/$ganador")
                            }
                        }
                    }
                ) {
                    Text(text = tablero[row][col], style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

fun verificarGanador(tablero: List<List<String>>, size: Int, jugador1: String, jugador2: String): String {
    val winCondition = 3

    // Verificar filas
    for (row in 0 until size) {
        for (col in 0..(size - winCondition)) {
            val subRow = tablero[row].subList(col, col + winCondition)
            if (subRow.all { it == "X" }) return jugador1
            if (subRow.all { it == "O" }) return jugador2
        }
    }

    // Verificar columnas
    for (col in 0 until size) {
        for (row in 0..(size - winCondition)) {
            val subCol = (0 until winCondition).map { tablero[row + it][col] }
            if (subCol.all { it == "X" }) return jugador1
            if (subCol.all { it == "O" }) return jugador2
        }
    }

    // Verificar diagonales principales
    for (row in 0..(size - winCondition)) {
        for (col in 0..(size - winCondition)) {
            val diagonal = (0 until winCondition).map { tablero[row + it][col + it] }
            if (diagonal.all { it == "X" }) return jugador1
            if (diagonal.all { it == "O" }) return jugador2
        }
    }

    // Verificar diagonales inversas
    for (row in 0..(size - winCondition)) {
        for (col in (winCondition - 1) until size) {
            val diagonal = (0 until winCondition).map { tablero[row + it][col - it] }
            if (diagonal.all { it == "X" }) return jugador1
            if (diagonal.all { it == "O" }) return jugador2
        }
    }

    return "" // No hay ganador
}