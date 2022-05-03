package com.sshevtsov.translator

import com.sshevtsov.translator.data.api.entity.DataModelResponse
import com.sshevtsov.translator.data.api.entity.MeaningResponse
import com.sshevtsov.translator.data.api.entity.TranslationResponse
import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import com.sshevtsov.translator.domain.entity.Translation
import com.sshevtsov.translator.domain.entity.UrlPath

class DataModelMappersDummies {

  val translationResponseFull = TranslationResponse(
    text = "кот",
    note = "кошка"
  )

  val translationFull = Translation(
    text = "кот",
    note = "кошка"
  )

  val translationResponseEmptyString = translationResponseFull.copy(
    note = ""
  )

  val translationResponseWithNull = translationResponseFull.copy(
    note = null
  )

  val translationWithNull = translationFull.copy(
    note = null
  )

  val meaningResponseFull = MeaningResponse(
    id = 3333,
    partOfSpeechCode = "n",
    translation = translationResponseFull,
    previewUrl = "//cat-preview.com",
    imageUrl = "//cat-image.com",
    transcription = "cat",
    soundUrl = "http://meow.com"
  )

  val meaningFull = Meaning(
    id = Meaning.Id(3333),
    partOfSpeechCode = "n",
    translation = translationFull,
    previewUrl = UrlPath("https://cat-preview.com"),
    imageUrl = UrlPath("https://cat-image.com"),
    transcription = "[cat]",
    soundUrl = UrlPath("http://meow.com")
  )

  val meaningResponseEmptyString = meaningResponseFull.copy(
    partOfSpeechCode = "",
    translation = translationResponseWithNull,
    previewUrl = "",
    imageUrl = "",
    transcription = "",
    soundUrl = ""
  )

  val meaningResponseWithNull = meaningResponseFull.copy(
    partOfSpeechCode = null,
    translation = translationResponseWithNull,
    previewUrl = null,
    imageUrl = null,
    transcription = null,
    soundUrl = null
  )

  val meaningWithNull = meaningFull.copy(
    partOfSpeechCode = null,
    translation = translationWithNull,
    previewUrl = null,
    imageUrl = null,
    transcription = null,
    soundUrl = null
  )

  val dataModelResponseFull = DataModelResponse(
    id = 5555,
    text = "cat",
    meanings = listOf(
      meaningResponseFull,
      meaningResponseFull,
      meaningResponseFull
    )
  )

  val dataModelFull = DataModel(
    id = DataModel.Id(5555),
    text = "cat",
    meanings = listOf(
      meaningFull,
      meaningFull,
      meaningFull
    )
  )

  val dataModelResponseWithEmptyMeanings = dataModelResponseFull.copy(
    meanings = emptyList()
  )

  val dataModelWithEmptyMeanings = dataModelFull.copy(
    meanings = emptyList()
  )
}