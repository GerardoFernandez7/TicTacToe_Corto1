package com.joseruiz.tictactoe.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InicioScreen(navController: NavController) {
    var jugador1 by remember { mutableStateOf("") }
    var jugador2 by remember { mutableStateOf("") }
    var size by remember { mutableStateOf(3) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campos para ingresar los nombres de los jugadores
        Text(text = "Jugador 1")
        OutlinedTextField(value = jugador1, onValueChange = {
            jugador1 = it
            errorMessage = ""
        })

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Jugador 2")
        OutlinedTextField(value = jugador2, onValueChange = {
            jugador2 = it
            errorMessage = ""
        })

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Seleccione el tamaño del tablero")

        // Mostrar tamaño seleccionado actualmente
        Text(
            text = "Tamaño seleccionado: ${size}x$size",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            // Botón para 3x3
            Button(
                onClick = { size = 3 },
                colors = if (size == 3) {
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                } else {
                    androidx.compose.material3.ButtonDefaults.buttonColors()
                }
            ) {
                Text("3x3")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para 4x4
            Button(
                onClick = { size = 4 },
                colors = if (size == 4) {
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                } else {
                    androidx.compose.material3.ButtonDefaults.buttonColors()
                }
            ) {
                Text("4x4")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para 5x5
            Button(
                onClick = { size = 5 },
                colors = if (size == 5) {
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                } else {
                    androidx.compose.material3.ButtonDefaults.buttonColors()
                }
            ) {
                Text("5x5")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de error si es necesario
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Botón para iniciar el juego
        Button(onClick = {
            // Validar que ambos jugadores hayan ingresado sus nombres
            if (jugador1.isEmpty() || jugador2.isEmpty()) {
                errorMessage = "Por favor, ingrese los nombres de ambos jugadores"
            } else {
                // Se envía la información por ruta si los nombres están correctos
                navController.navigate("JuegoActivity/$jugador1/$jugador2/$size")
            }
        }) {
            Text("Iniciar Juego")
        }
    }
}