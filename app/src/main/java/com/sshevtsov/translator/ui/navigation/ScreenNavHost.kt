package com.sshevtsov.translator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sshevtsov.translator.ui.screens.main.MainScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Screen.Main.name
  ) {
    composable(route = Screen.Main.name) { MainScreen(viewModel = getViewModel()) }
  }
}