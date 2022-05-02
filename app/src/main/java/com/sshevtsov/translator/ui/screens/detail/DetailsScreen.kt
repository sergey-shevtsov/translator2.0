package com.sshevtsov.translator.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sshevtsov.translator.ui.components.DetailsInfo
import com.sshevtsov.translator.ui.components.SquareImage

@Composable
fun DetailsScreen(
  viewModel: DetailsViewModel,
) {
  val state: ViewState by viewModel.state.collectAsState(ViewState())
  Column(
    modifier = Modifier.fillMaxWidth()
  ) {
    if (state.imageUrl != null) {
      SquareImage(
        modifier = Modifier.fillMaxWidth(),
        url = state.imageUrl!!
      )
    }
    DetailsInfo(
      text = state.text,
      translation = state.translation,
      note = state.note,
      transcription = state.transcription,
      speechCode = state.partOfSpeechResId?.let { stringResource(it) },
      soundUrl = state.soundUrl,
      onSoundClick = { viewModel.playSound() }
    )
  }
}