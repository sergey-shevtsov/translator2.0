package com.sshevtsov.translator.data.repository

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.api.mapper.toDomainModel
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository
import kotlinx.coroutines.channels.Channel

class TranslatorRepositoryImpl(
  private val translatorApi: TranslatorApi
) : TranslatorRepository {

  override val searchResults = Channel<List<DataModel>>()

  override suspend fun search(wordToSearch: String) {
    searchResults.send(
      translatorApi.search(wordToSearch).map {
        it.toDomainModel()
      }
    )
  }
}
