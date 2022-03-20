package com.sshevtsov.translator.data.repository

import com.sshevtsov.translator.data.api.TranslatorApi
import com.sshevtsov.translator.data.api.mapper.DataModelMapper
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository

class TranslatorRepositoryImpl(
  private val translatorApi: TranslatorApi
) : TranslatorRepository {

  override suspend fun search(wordToSearch: String): Array<DataModel> =
    translatorApi.search(wordToSearch)
      .map { DataModelMapper.toDomain(it) }
      .toTypedArray()
}