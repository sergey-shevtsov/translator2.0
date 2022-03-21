package com.sshevtsov.translator.domain.repository

import com.sshevtsov.translator.domain.entity.DataModel

interface TranslatorRepository {
  suspend fun search(wordToSearch: String): List<DataModel>
}