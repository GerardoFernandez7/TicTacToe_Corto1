package com.joseruiz.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joseruiz.tictactoe.screens.FinalScreen
import com.joseruiz.tictactoe.screens.InicioScreen
import com.joseruiz.tictactoe.screens.JuegoScreen
import com.joseruiz.tictactoe.screens.PortadaScreen
import com.joseruiz.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "PortadaActivity") {
                    composable("PortadaActivity") {
                        PortadaScreen(navController = navController)
                    }
                    composable("InicioActivity") {
                        InicioScreen(navController = navController)
                    }
                    composable("JuegoActivity/{jugador1}/{jugador2}/{size}") { backStackEntry ->
                        val jugador1 = backStackEntry.arguments?.getString("jugador1") ?: ""
                        val jugador2 = backStackEntry.arguments?.getString("jugador2") ?: ""
                        val size = backStackEntry.arguments?.getString("size")?.toInt() ?: 3
                        JuegoScreen(jugador1, jugador2, size, navController)
                    }
                    composable("FinalActivity/{ganador}") { backStackEntry ->
                        val ganador = backStackEntry.arguments?.getString("ganador") ?: ""
                        FinalScreen(ganador, navController)
                    }
                }
            }
        }
    }
}



