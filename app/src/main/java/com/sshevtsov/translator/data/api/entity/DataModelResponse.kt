package com.sshevtsov.translator.data.api.entity

import com.google.gson.annotations.SerializedName

data class DataModelResponse(
  @field:SerializedName("id") val id: Int?,
  @field:SerializedName("text") val text: String?,
  @field:SerializedName("meanings") val meanings: Array<MeaningResponse?>?
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as DataModelResponse

    if (id != other.id) return false
    if (text != other.text) return false
    if (meanings != null) {
      if (other.meanings == null) return false
      if (!meanings.contentEquals(other.meanings)) return false
    } else if (other.meanings != null) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id ?: 0
    result = 31 * result + (text?.hashCode() ?: 0)
    result = 31 * result + (meanings?.contentHashCode() ?: 0)
    return result
  }
}

data class MeaningResponse(
  @field:SerializedName("id") val id: Int?,
  @field:SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
  @field:SerializedName("translation") val translation: TranslationResponse?,
  @field:SerializedName("previewUrl") val previewUrl: String?,
  @field:SerializedName("imageUrl") val imageUrl: String?,
  @field:SerializedName("transcription") val transcription: String?,
  @field:SerializedName("soundUrl") val soundUrl: String?
)

data class TranslationResponse(
  @field:SerializedName("text") val text: String?,
  @field:SerializedName("note") val note: String?
)