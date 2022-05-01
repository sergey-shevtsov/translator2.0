package com.sshevtsov.translator.domain.repository

import com.sshevtsov.translator.domain.entity.DataModel
import kotlinx.coroutines.channels.Channel

interface TranslatorRepository {
  val searchResults: Channel<List<DataModel>>
  suspend fun search(wordToSearch: String)
}
