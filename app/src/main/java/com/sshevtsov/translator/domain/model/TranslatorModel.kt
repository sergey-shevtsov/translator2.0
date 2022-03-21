package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel

interface TranslatorModel {
  suspend fun search(wordToSearch: String): List<DataModel>
}