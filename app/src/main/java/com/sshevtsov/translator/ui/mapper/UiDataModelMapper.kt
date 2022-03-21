package com.sshevtsov.translator.ui.mapper

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.entity.UiMeaning
import com.sshevtsov.translator.ui.entity.UiTranslation

object UiDataModelMapper {

  fun toUi(dataModel: DataModel): UiDataModel =
    UiDataModel(
      id = dataModel.id,
      text = dataModel.text,
      meanings = dataModel.meanings.map { toUi(it) }
    )

  private fun toUi(meaning: Meaning): UiMeaning =
    UiMeaning(
      id = meaning.id,
      partOfSpeechCode = meaning.partOfSpeechCode.fullSpeechCode,
      translation = toUi(meaning.translation),
      previewUrl = meaning.previewUrl,
      imageUrl = meaning.imageUrl,
      transcription = meaning.transcription,
      soundUrl = meaning.soundUrl
    )

  private fun toUi(translation: Translation): UiTranslation =
    UiTranslation(
      text = translation.text,
      note = translation.note
    )
}

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