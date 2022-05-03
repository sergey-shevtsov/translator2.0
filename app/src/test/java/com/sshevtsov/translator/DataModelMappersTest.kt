package com.sshevtsov.translator

import com.sshevtsov.translator.data.api.mapper.extractTranscription
import com.sshevtsov.translator.data.api.mapper.formatImageUrl
import com.sshevtsov.translator.data.api.mapper.toDomainModel
import com.sshevtsov.translator.domain.entity.UrlPath
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DataModelMappersTest {

  private val dummies = DataModelMappersDummies()

  @Test
  fun translationResponseToDomainModel_ReturnsMappedDomainTranslation() = with(dummies) {
    assertEquals(translationFull, translationResponseFull.toDomainModel())
  }

  @Test
  fun translationResponseToDomainModel_EmptyString_ReturnsMappedDomainTranslationWithNull() = with(dummies) {
    assertEquals(translationWithNull, translationResponseEmptyString.toDomainModel())
  }

  @Test
  fun translationResponseToDomainModel_WithNull_ReturnsMappedDomainTranslation() = with(dummies) {
    assertEquals(translationWithNull, translationResponseWithNull.toDomainModel())
  }

  @Test
  fun meaningResponseToDomainModel_ReturnsMappedDomainMeaning() = with(dummies) {
    assertEquals(meaningFull, meaningResponseFull.toDomainModel())
  }

  @Test
  fun meaningResponseToDomainModel_EmptyString_ReturnsMappedDomainMeaningWithNull() = with(dummies) {
    assertEquals(meaningWithNull, meaningResponseEmptyString.toDomainModel())
  }

  @Test
  fun meaningResponseToDomainModel_WithNull_ReturnsMappedDomainMeaning() = with(dummies) {
    assertEquals(meaningWithNull, meaningResponseWithNull.toDomainModel())
  }

  @Test
  fun dataModelResponseToDomainModel_ReturnsMappedDomainDataModel() = with(dummies) {
    assertEquals(dataModelFull, dataModelResponseFull.toDomainModel())
  }

  @Test
  fun dataModelResponseToDomainModel_WithEmptyMeanings_ReturnsMappedDomainDataModel() = with(dummies) {
    assertEquals(dataModelWithEmptyMeanings, dataModelResponseWithEmptyMeanings.toDomainModel())
  }

  @Test
  fun extractTranscription_ValidString_ReturnsWithSquareBrackets() {
    assertEquals("[transcription]", extractTranscription("transcription"))
  }

  @Test
  fun extractTranscription_EmptyString_ReturnsNull() {
    assertNull(extractTranscription(""))
  }

  @Test
  fun extractTranscription_Null_ReturnsNull() {
    assertNull(extractTranscription(null))
  }

  @Test
  fun formatImageUrl_UrlWithHttpsPrefix_ReturnsValidUrlPath() {
    assertEquals(UrlPath("https://site.com"), formatImageUrl("https://site.com"))
  }

  @Test
  fun formatImageUrl_UrlWithoutHttpsPrefix_ReturnsValidUrlPath() {
    assertEquals(UrlPath("https://site.com"), formatImageUrl("//site.com"))
  }

  @Test
  fun formatImageUrl_EmptyString_ReturnsNull() {
    assertNull(formatImageUrl(""))
  }

  @Test
  fun formatImageUrl_Null_ReturnsNull() {
    assertNull(formatImageUrl(null))
  }
}
