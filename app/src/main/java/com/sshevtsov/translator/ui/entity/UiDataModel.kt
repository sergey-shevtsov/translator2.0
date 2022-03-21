package com.sshevtsov.translator.ui.entity

data class UiDataModel(
  val id: Int,
  val text: String,
  val meanings: List<UiMeaning>
)

data class UiMeaning(
  val id: Int,
  val partOfSpeechCode: String,
  val translation: UiTranslation,
  val previewUrl: String,
  val imageUrl: String,
  val transcription: String,
  val soundUrl: String
)

data class UiTranslation(
  val text: String,
  val note: String?
)