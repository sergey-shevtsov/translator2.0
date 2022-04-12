package com.sshevtsov.translator.data.repository

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.api.entity.DataModelResponse
import com.sshevtsov.translator.data.api.mapper.toDomainModel
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class TranslatorRepositoryImpl(
  private val translatorApi: TranslatorApi
) : TranslatorRepository {

  private val searchResults = MutableStateFlow<List<DataModelResponse>?>(null)

  override suspend fun search(wordToSearch: String) {
    searchResults.value = translatorApi.search(wordToSearch)
  }

  override fun searchResults(): Flow<List<DataModel>?> =
    searchResults.map { list ->
      list?.map { it.toDomainModel() }
    }

}
