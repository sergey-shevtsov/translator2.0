package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class TranslatorModelImpl(
  private val repository: TranslatorRepository
) : TranslatorModel {
  private val coroutineScope = MutableStateFlow<CoroutineScope?>(null)

  override fun start(scope: CoroutineScope) {
    coroutineScope.value = scope
  }

  override fun search(wordToSearch: String) {
    requireNotNull(coroutineScope.value) { "You should start TranslatorModel before searching" }
    coroutineScope.value!!.launch {
      repository.search(wordToSearch)
    }
  }

  override fun searchResults(): Flow<List<DataModel>> =
    repository.searchResults().filterNotNull()
}
