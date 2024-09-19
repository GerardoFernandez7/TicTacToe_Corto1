package com.joseruiz.tictactoe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InicioScreen(navController: NavController) {
    var jugador1 by remember { mutableStateOf("") }
    var jugador2 by remember { mutableStateOf("") }
    var size by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Jugador 1")
        TextField(value = jugador1, onValueChange = { jugador1 = it })

        Text(text = "Jugador 2")
        TextField(value = jugador2, onValueChange = { jugador2 = it })

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Seleccione el tamaño del tablero")
        Row {
            Button(onClick = { size = 3 }) { Text("3x3") }
            Button(onClick = { size = 4 }) { Text("4x4") }
            Button(onClick = { size = 5 }) { Text("5x5") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Guardar nombres y tamaño
            navController.navigate("JuegoActivity/$jugador1/$jugador2/$size")
        }) {
            Text("Iniciar Juego")
        }
    }
}
