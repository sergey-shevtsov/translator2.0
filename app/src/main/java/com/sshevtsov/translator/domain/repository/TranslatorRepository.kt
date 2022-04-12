package com.sshevtsov.translator.domain.repository

import com.sshevtsov.translator.domain.entity.DataModel
import kotlinx.coroutines.flow.Flow

interface TranslatorRepository {
  suspend fun search(wordToSearch: String)
  fun searchResults(): Flow<List<DataModel>?>
}
