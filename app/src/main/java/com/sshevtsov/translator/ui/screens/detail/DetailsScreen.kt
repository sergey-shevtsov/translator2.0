package com.sshevtsov.translator.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(
  viewModel: DetailsViewModel
) {
  val state: ViewState by viewModel.state.collectAsState(ViewState())
}