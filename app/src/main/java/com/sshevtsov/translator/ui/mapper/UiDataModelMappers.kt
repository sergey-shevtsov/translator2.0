package com.sshevtsov.translator.ui.mapper

import com.sshevtsov.translator.R
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.entity.UiMeaning
import com.sshevtsov.translator.ui.entity.UiTranslation

fun DataModel.toUiModel(): UiDataModel =
  UiDataModel(
    id = UiDataModel.Id(this.id.value),
    text = this.text,
    meanings = this.meanings.map { it.toUiModel() }
  )

private fun Meaning.toUiModel(): UiMeaning =
  UiMeaning(
    id = UiMeaning.Id(this.id.value),
    speechCodeResId = this.partOfSpeechCode?.speechCodeResId,
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

val String.speechCodeResId: Int
  get() {
    return when (this) {
      "n" -> R.string.speech_code_noun
      "v" -> R.string.speech_code_verb
      "j" -> R.string.speech_code_adjective
      "r" -> R.string.speech_code_adverb
      "prp" -> R.string.speech_code_preposition
      "prn" -> R.string.speech_code_pronoun
      "crd" -> R.string.speech_code_cardinal_number
      "cjc" -> R.string.speech_code_conjunction
      "exc" -> R.string.speech_code_interjection
      "det" -> R.string.speech_code_article
      "abb" -> R.string.speech_code_abbreviation
      "x" -> R.string.speech_code_particle
      "ord" -> R.string.speech_code_ordinal_number
      "md" -> R.string.speech_code_modal_verb
      "ph" -> R.string.speech_code_phrase
      "phi" -> R.string.speech_code_idiom
      else -> 0
    }
  }
