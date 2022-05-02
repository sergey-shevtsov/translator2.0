package com.sshevtsov.translator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sshevtsov.translator.R
import com.sshevtsov.translator.domain.entity.UrlPath

@Composable
fun DetailsInfo(
  modifier: Modifier = Modifier,
  text: String,
  translation: String,
  note: String? = null,
  transcription: String? = null,
  speechCode: String? = null,
  soundUrl: UrlPath? = null,
  onSoundClick: () -> Unit
) {
  Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
    Text(
      text = text,
      style = MaterialTheme.typography.h5
    )
    Text(
      text = translation,
      style = MaterialTheme.typography.h4
    )
    if (note != null) {
      Text(
        text = note,
        style = MaterialTheme.typography.h5
      )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (speechCode != null) {
        Text(text = speechCode)
      }
      Spacer(modifier = Modifier.width(16.dp))
      if (transcription != null) {
        Text(text = transcription)
      }
      if (soundUrl != null) {
        IconButton(onClick = onSoundClick) {
          Icon(
            painter = painterResource(id = R.drawable.ic_sound_24),
            contentDescription = "sound icon"
          )
        }
      }
    }
  }
}