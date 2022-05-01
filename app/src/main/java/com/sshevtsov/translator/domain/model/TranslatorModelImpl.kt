package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class TranslatorModelImpl(
  private val repository: TranslatorRepository
) : TranslatorModel {
  private var coroutineScope: CoroutineScope? = null

  override fun start(scope: CoroutineScope) {
    coroutineScope = scope
  }

  override fun search(wordToSearch: String) {
    requireNotNull(coroutineScope) { "You should start TranslatorModel before searching" }
    coroutineScope!!.launch {
      repository.search(wordToSearch)
    }
  }

  override fun searchResults(): Channel<List<DataModel>> = repository.searchResults
}
