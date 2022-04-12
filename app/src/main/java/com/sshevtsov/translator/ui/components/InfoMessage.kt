package com.sshevtsov.translator.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun InfoMessage(
  modifier: Modifier = Modifier,
  @StringRes
  textResId: Int
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 32.dp),
      text = stringResource(id = textResId),
      style = MaterialTheme.typography.body2
    )
  }
}
