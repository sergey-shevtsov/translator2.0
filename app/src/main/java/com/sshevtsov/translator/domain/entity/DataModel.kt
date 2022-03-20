package com.sshevtsov.translator.domain.entity

data class DataModel(
  val id: Int,
  val text: String,
  val meanings: Array<Meaning>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as DataModel

    if (id != other.id) return false
    if (text != other.text) return false
    if (!meanings.contentEquals(other.meanings)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + text.hashCode()
    result = 31 * result + meanings.contentHashCode()
    return result
  }
}

data class Meaning(
  val id: Int,
  val partOfSpeechCode: String,
  val translation: Translation,
  val previewUrl: String,
  val imageUrl: String,
  val transcription: String,
  val soundUrl: String
)

data class Translation(
  val text: String,
  val note: String?
)