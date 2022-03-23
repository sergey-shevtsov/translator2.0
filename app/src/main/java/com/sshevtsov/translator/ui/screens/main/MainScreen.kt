package com.sshevtsov.translator.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sshevtsov.translator.ui.entity.UiDataModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
  val state by viewModel.state.collectAsState(ViewState())
  if (state.infoMessage != null) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text(text = state.infoMessage!!)
    }
  }
}

@Immutable
data class ViewState(
  val currentQuery: String = "",
  val resultList: List<UiDataModel> = emptyList(),
  val infoMessage: String? = null,
  val progressIsVisible: Boolean = false,
  val errorIsVisible: Boolean = false,
  val errorMessage: String? = null
)