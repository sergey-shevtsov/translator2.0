package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel

interface TranslatorModel {
  fun start(scope: CoroutineScope)
  fun search(wordToSearch: String)
  fun searchResults(): Channel<List<DataModel>>
}
