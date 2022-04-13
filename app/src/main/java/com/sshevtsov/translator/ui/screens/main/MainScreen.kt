package com.sshevtsov.translator.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sshevtsov.translator.R
import com.sshevtsov.translator.ui.components.DataModelList
import com.sshevtsov.translator.ui.components.InfoMessage
import com.sshevtsov.translator.ui.components.SearchProgress
import com.sshevtsov.translator.ui.components.SearchTextField

@Composable
fun MainScreen(viewModel: MainViewModel) {
  val state by viewModel.state.collectAsState(ViewState())

  Column {
    SearchTextField(
      modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 8.dp)
        .fillMaxWidth(),
      currentQuery = state.currentQuery,
      onQueryChanged = { viewModel.changeQuery(it) },
      onSearch = { viewModel.search(state.currentQuery) },
      label = stringResource(R.string.main_enter_query)
    )
    when {
      state.progressIsVisible -> SearchProgress()
      state.errorIsVisible -> { /* TODO() */ }
      state.infoMessageIsVisible && state.infoMessageResId != null -> {
        InfoMessage(textResId = state.infoMessageResId!!)
      }
      else -> {
        DataModelList(
          list = state.resultList,
          expandedIds = state.expandedIds,
          onExpandClick = { id -> viewModel.expandDataModel(id) },
          onMeaningClick = { /* TODO() */ }
        )
      }
    }
  }
}
