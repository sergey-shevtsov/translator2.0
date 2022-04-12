package com.sshevtsov.translator.data.api.mapper

import com.sshevtsov.translator.data.api.entity.DataModelResponse
import com.sshevtsov.translator.data.api.entity.MeaningResponse
import com.sshevtsov.translator.data.api.entity.TranslationResponse
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation
import com.sshevtsov.translator.domain.entity.UrlPath

fun DataModelResponse.toDomainModel(): DataModel {
  return DataModel(
    id = DataModel.Id(this.id),
    text = this.text,
    meanings = this.meanings.map { it.toDomainModel() }
  )
}

private fun MeaningResponse.toDomainModel(): Meaning {
  return Meaning(
    id = Meaning.Id(this.id),
    partOfSpeechCode = this.partOfSpeechCode?.takeIf { it.isNotBlank() },
    translation = this.translation.toDomainModel(),
    previewUrl = this.previewUrl?.let { UrlPath(it) }.takeIf { it?.value?.isNotBlank() == true },
    imageUrl = this.imageUrl?.let { UrlPath(it) }.takeIf { it?.value?.isNotBlank() == true },
    transcription = this.transcription.orEmpty(),
    soundUrl = this.soundUrl?.let { UrlPath(it) }.takeIf { it?.value?.isNotBlank() == true },
  )
}

private fun TranslationResponse.toDomainModel(): Translation {
  return Translation(
    text = this.text,
    note = this.note?.takeIf { it.isNotBlank() }
  )
}
