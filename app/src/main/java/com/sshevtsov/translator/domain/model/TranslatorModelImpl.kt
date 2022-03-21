package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.repository.TranslatorRepository

class TranslatorModelImpl(
  private val repository: TranslatorRepository
) : TranslatorModel {

  override suspend fun search(wordToSearch: String): List<DataModel> =
    repository.search(wordToSearch)
}