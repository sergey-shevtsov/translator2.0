package com.sshevtsov.translator.domain.model

import com.sshevtsov.translator.domain.entity.DataModel
import com.sshevtsov.translator.domain.entity.Meaning
import kotlinx.coroutines.flow.Flow

interface TranslatorModel {
  fun search(wordToSearch: String)
  fun searchResults(): Flow<List<DataModel>>
  fun setChosenIds(dataModelId: DataModel.Id, meaningId: Meaning.Id)
  fun chosenDataModel(): Flow<DataModel>
  fun chosenMeaning(): Flow<Meaning>
}
