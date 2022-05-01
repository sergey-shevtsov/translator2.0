package com.sshevtsov.translator.ui.screens.query

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sshevtsov.translator.R
import com.sshevtsov.translator.ui.components.DataModelList
import com.sshevtsov.translator.ui.components.ErrorMessage
import com.sshevtsov.translator.ui.components.InfoMessage
import com.sshevtsov.translator.ui.components.SearchProgress
import com.sshevtsov.translator.ui.components.SearchTextField
import com.sshevtsov.translator.ui.navigation.Screen

@Composable
fun QueryScreen(
  viewModel: QueryViewModel,
  navController: NavHostController
) {
  val state: ViewState by viewModel.state.collectAsState(ViewState())

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
      state.errorMessageResId != null -> {
        ErrorMessage(textResId = state.errorMessageResId!!)
      }
      state.infoMessageResId != null -> {
        InfoMessage(textResId = state.infoMessageResId!!)
      }
      state.result.isNotEmpty() -> {
        DataModelList(
          list = state.result,
          expandedIds = state.expandedIds,
          onExpandClick = { id -> viewModel.expandDataModel(id) },
          onMeaningClick = { dataModelId, meaningId ->
            viewModel.openDetails(dataModelId, meaningId)
            navController.navigate(Screen.Details.name)
          }
        )
      }
      else -> error("If the result list is empty, then a progress, info message, or error message should be visible")
    }
  }
}
