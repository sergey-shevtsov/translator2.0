package com.sshevtsov.translator.ui.mapper

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.entity.UiMeaning
import com.sshevtsov.translator.ui.entity.UiTranslation

fun DataModel.toUiModel(): UiDataModel =
  UiDataModel(
    id = UiDataModel.Id(this.id),
    text = this.text,
    meanings = this.meanings.map { it.toUiModel() }
  )

private fun Meaning.toUiModel(): UiMeaning =
  UiMeaning(
    id = UiMeaning.Id(this.id),
    partOfSpeechCode = this.partOfSpeechCode?.fullSpeechCode,
    translation = this.translation.toUiModel(),
    previewUrl = this.previewUrl,
    imageUrl = this.imageUrl,
    transcription = this.transcription,
    soundUrl = this.soundUrl
  )

private fun Translation.toUiModel(): UiTranslation =
  UiTranslation(
    text = this.text,
    note = this.note
  )

private val String.fullSpeechCode: String
  get() {
    return when (this) {
      "n" -> "noun"
      "v" -> "verb"
      "j" -> "adjective"
      "r" -> "adverb"
      "prp" -> "preposition"
      "prn" -> "pronoun"
      "crd" -> "cardinal number"
      "cjc" -> "conjunction"
      "exc" -> "interjection"
      "det" -> "article"
      "abb" -> "abbreviation"
      "x" -> "particle"
      "ord" -> "ordinal number"
      "md" -> "modal verb"
      "ph" -> "phrase"
      "phi" -> "idiom"
      else -> ""
    }
  }
