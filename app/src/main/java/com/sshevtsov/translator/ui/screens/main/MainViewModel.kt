package com.sshevtsov.translator.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshevtsov.translator.domain.model.TranslatorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
  private val translatorModel: TranslatorModel
) : ViewModel() {

  private val _state = MutableStateFlow(ViewState())
  val state: Flow<ViewState> get() = _state

  init {
    _state.update {
      it.copy(infoMessage = "Введите запрос в текстовом поле для поиска")
    }
  }

  fun search(wordToSearch: String) = viewModelScope.launch {

  }
}