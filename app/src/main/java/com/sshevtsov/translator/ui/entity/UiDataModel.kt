package com.sshevtsov.translator.ui.entity

import com.sshevtsov.translator.domain.entity.UrlPath

data class UiDataModel(
  val id: Id,
  val text: String,
  val meanings: List<UiMeaning>
) {
  @JvmInline
  value class Id(val value: Any)
}

data class UiMeaning(
  val id: Id,
  val partOfSpeechCode: String?,
  val translation: UiTranslation,
  val previewUrl: UrlPath?,
  val imageUrl: UrlPath?,
  val transcription: String?,
  val soundUrl: UrlPath?
) {
  @JvmInline
  value class Id(val value: Any)
}

data class UiTranslation(
  val text: String,
  val note: String?
)
