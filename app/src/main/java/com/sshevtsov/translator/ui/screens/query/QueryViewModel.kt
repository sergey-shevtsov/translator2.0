package com.sshevtsov.translator.ui.screens.query

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshevtsov.translator.R
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.entity.UiMeaning
import com.sshevtsov.translator.ui.mapper.toUiModel
import com.sshevtsov.translator.util.ConnectionStatus
import com.sshevtsov.translator.util.NetworkManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QueryViewModel(
  private val model: TranslatorModel,
  private val networkManager: NetworkManager
) : ViewModel() {

  private var previousQuery = ""
  private var queryJob: Job? = null

  private val _state = MutableStateFlow(ViewState())
  val state: Flow<ViewState> get() = _state

  init {
    subscribeToSearchResult()
  }

  private fun subscribeToSearchResult() {
    model.searchResults()
      .catch {
        _state.update {
          _state.value.copy(
            errorMessageResId = R.string.common_error_message,
            progressIsVisible = false
          )
        }
      }
      .onEach { list ->
        _state.update {
          if (list.isEmpty()) {
            it.copy(
              infoMessageResId = R.string.main_empty_result_message,
              progressIsVisible = false
            )
          } else {
            it.copy(
              result = list.map { dataModel -> dataModel.toUiModel() },
              progressIsVisible = false
            )
          }
        }
      }
      .launchIn(viewModelScope)
  }

  fun openDetails(dataModelId: UiDataModel.Id, meaningId: UiMeaning.Id) {
    model.setChosenIds(
      dataModelId = DataModel.Id(dataModelId.value),
      meaningId = Meaning.Id(meaningId.value)
    )
  }

  fun changeQuery(query: String) {
    _state.update {
      it.copy(currentQuery = query)
    }
    queryJob?.cancel()
    queryJob = viewModelScope.launch {
      delay(500)
      search(query)
    }
  }

  fun expandDataModel(id: UiDataModel.Id) {
    _state.update { state ->
      state.copy(
        expandedIds = if (state.expandedIds.contains(id)) {
          state.expandedIds.minus(id)
        } else {
          state.expandedIds.plus(id)
        }
      )
    }
  }

  fun search(query: String) {
    if (query == previousQuery || query.isBlank()) return
    previousQuery = query
    when (networkManager.getConnectionStatus()) {
      ConnectionStatus.Connected -> {
        _state.update { state ->
          state.copy(
            infoMessageResId = null,
            errorMessageResId = null,
            progressIsVisible = true
          )
        }
        model.search(query)
      }
      ConnectionStatus.Lost -> {
        previousQuery = ""
        _state.update { state ->
          state.copy(
            infoMessageResId = null,
            errorMessageResId = R.string.network_error_message
          )
        }
      }
    }

  }
}

@Immutable
data class ViewState(
  val currentQuery: String = "",
  val result: List<UiDataModel> = emptyList(),
  val expandedIds: Set<UiDataModel.Id> = emptySet(),
  val progressIsVisible: Boolean = false,
  @StringRes val infoMessageResId: Int? = R.string.main_no_query_message,
  @StringRes val errorMessageResId: Int? = null,
)
