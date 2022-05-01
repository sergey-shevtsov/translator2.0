package com.sshevtsov.translator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sshevtsov.translator.ui.screens.detail.DetailsScreen
import com.sshevtsov.translator.ui.screens.query.QueryScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Screen.Query.name
  ) {
    composable(route = Screen.Query.name) {
      QueryScreen(viewModel = getViewModel(), navController = navController)
    }
    composable(route = Screen.Details.name) {
      DetailsScreen(viewModel = getViewModel())
    }
  }
}