package com.sshevtsov.translator.ui.screens.detail

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshevtsov.translator.domain.entity.UrlPath
import com.sshevtsov.translator.domain.model.TranslatorModel
import com.sshevtsov.translator.ui.mapper.speechCodeResId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip

class DetailsViewModel(
  model: TranslatorModel
) : ViewModel() {
  private val _state = MutableStateFlow(ViewState())
  val state: Flow<ViewState> get() = _state

  init {
    model.chosenDataModel()
      .zip(model.chosenMeaning()) { dataModel, meaning ->
        _state.update { viewState ->
          viewState.copy(
            text = dataModel.text,
            translation = meaning.translation.text,
            note = meaning.translation.note,
            partOfSpeechResId = meaning.partOfSpeechCode?.speechCodeResId,
            imageUrl = meaning.imageUrl,
            transcription = meaning.transcription,
            soundUrl = meaning.soundUrl
          )
        }
      }
      .launchIn(viewModelScope)
  }
}

@Immutable
data class ViewState(
  val text: String = "",
  val translation: String = "",
  val note: String? = null,
  @StringRes val partOfSpeechResId: Int? = null,
  val imageUrl: UrlPath? = null,
  val transcription: String? = null,
  val soundUrl: UrlPath? = null
)