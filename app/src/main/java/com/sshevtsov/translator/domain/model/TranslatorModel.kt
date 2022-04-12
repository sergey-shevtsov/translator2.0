package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface TranslatorModel {
  fun start(scope: CoroutineScope)
  fun search(wordToSearch: String)
  fun searchResults(): Flow<List<DataModel>>
}
