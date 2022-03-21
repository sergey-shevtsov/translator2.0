package com.sshevtsov.translator.data.api.entity

import com.google.gson.annotations.SerializedName

data class DataModelResponse(
  @field:SerializedName("id") val id: Int?,
  @field:SerializedName("text") val text: String?,
  @field:SerializedName("meanings") val meanings: List<MeaningResponse?>
)

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