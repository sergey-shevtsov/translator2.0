package com.sshevtsov.translator.domain.entity

data class DataModel(
  val id: Id,
  val text: String,
  val meanings: List<Meaning>
) {
  @JvmInline
  value class Id(val value: Any)
}

data class Meaning(
  val id: Id,
  val partOfSpeechCode: String?,
  val translation: Translation,
  val previewUrl: UrlPath?,
  val imageUrl: UrlPath?,
  val transcription: String?,
  val soundUrl: UrlPath?
) {
  @JvmInline
  value class Id(val value: Any)
}

data class Translation(
  val text: String,
  val note: String?
)
