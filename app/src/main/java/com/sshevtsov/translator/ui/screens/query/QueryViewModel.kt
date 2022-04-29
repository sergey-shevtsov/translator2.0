package com.sshevtsov.translator.ui.screens.query

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshevtsov.translator.R
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.mapper.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QueryViewModel(
  private val translatorModel: TranslatorModel
) : ViewModel() {

  private val _state = MutableStateFlow(ViewState())
  val state: Flow<ViewState> get() = _state

  init {
    _state.update {
      it.copy(
        infoMessageResId = R.string.main_no_query_message,
        infoMessageIsVisible = true
      )
    }
    translatorModel.start(viewModelScope)
    translatorModel.searchResults()
      .onEach { list ->
        _state.update {
          if (list.isEmpty()) {
            it.copy(
              infoMessageResId = R.string.main_empty_result_message,
              infoMessageIsVisible = true,
              errorMessage = null,
              errorIsVisible = false,
              progressIsVisible = false
            )
          } else {
            it.copy(
              resultList = list.map { dataModel -> dataModel.toUiModel() },
              infoMessageResId = null,
              infoMessageIsVisible = false,
              errorMessage = null,
              errorIsVisible = false,
              progressIsVisible = false
            )
          }
        }
      }
      .launchIn(viewModelScope)
  }

  fun changeQuery(query: String) {
    _state.update {
      it.copy(currentQuery = query)
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

  fun search(wordToSearch: String) = viewModelScope.launch {
    _state.update { state ->
      state.copy(
        infoMessageResId = null,
        infoMessageIsVisible = false,
        errorMessage = null,
        errorIsVisible = false,
        progressIsVisible = true
      )
    }
    translatorModel.search(wordToSearch)
  }
}

@Immutable
data class ViewState(
  val currentQuery: String = "",
  val resultList: List<UiDataModel> = emptyList(),
  val expandedIds: Set<UiDataModel.Id> = emptySet(),
  @DrawableRes
  val infoMessageResId: Int? = null,
  val infoMessageIsVisible: Boolean = false,
  val progressIsVisible: Boolean = false,
  val errorIsVisible: Boolean = false,
  val errorMessage: String? = null
)
