package com.sshevtsov.translator.data.api.mapper

import com.sshevtsov.translator.data.api.entity.DataModelResponse
import com.sshevtsov.translator.data.api.entity.MeaningResponse
import com.sshevtsov.translator.data.api.entity.TranslationResponse
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation

object DataModelMapper {

  fun toDomain(dataModelResponse: DataModelResponse): DataModel {
    return DataModel(
      id = dataModelResponse.id ?: 0,
      text = dataModelResponse.text.orEmpty(),
      meanings =dataModelResponse.meanings
          .filterNotNull()
          .map { toDomain(it) }
    )
  }

  private fun toDomain(meaningResponse: MeaningResponse): Meaning {
    return Meaning(
      id = meaningResponse.id ?: 0,
      partOfSpeechCode = meaningResponse.partOfSpeechCode.orEmpty(),
      translation = if (meaningResponse.translation != null) {
        toDomain(meaningResponse.translation)
      } else {
        Translation("", null)
      },
      previewUrl = meaningResponse.previewUrl.orEmpty(),
      imageUrl = meaningResponse.imageUrl.orEmpty(),
      transcription = meaningResponse.transcription.orEmpty(),
      soundUrl = meaningResponse.soundUrl.orEmpty()
    )
  }

  private fun toDomain(translationResponse: TranslationResponse): Translation {
    return Translation(
      text = translationResponse.text.orEmpty(),
      note = if (translationResponse.note.isNullOrBlank()) {
        null
      } else {
        translationResponse.note
      }
    )
  }
}