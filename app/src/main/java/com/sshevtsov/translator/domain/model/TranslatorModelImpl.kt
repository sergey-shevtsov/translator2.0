package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TranslatorModelImpl(
  private val repository: TranslatorRepository,
  private val coroutineScope: CoroutineScope
) : TranslatorModel {
  private val _state = MutableStateFlow(State())

  override fun search(wordToSearch: String) {
    coroutineScope.launch {
      repository.search(wordToSearch)
    }
  }

  override fun searchResults(): Flow<List<DataModel>> =
    repository.searchResults.consumeAsFlow()
      .onEach { result ->
        _state.update { state ->
          state.copy(lastResult = result)
        }
      }

  override fun setChosenIds(dataModelId: DataModel.Id, meaningId: Meaning.Id) {
    _state.update { state ->
      state.copy(
        chosenDataModelId = dataModelId,
        chosenMeaningId = meaningId
      )
    }
  }

  override fun chosenDataModel(): Flow<DataModel> =
    _state.mapNotNull { state ->
      state.lastResult?.firstOrNull { dataModel ->
        state.chosenDataModelId == dataModel.id
      }
    }

  override fun chosenMeaning(): Flow<Meaning> =
    chosenDataModel().mapNotNull { dataModel ->
      dataModel.meanings.firstOrNull { meaning ->
        meaning.id == _state.value.chosenMeaningId
      }
    }

  data class State(
    val chosenDataModelId: DataModel.Id? = null,
    val chosenMeaningId: Meaning.Id? = null,
    val lastResult: List<DataModel>? = null
  )
}
