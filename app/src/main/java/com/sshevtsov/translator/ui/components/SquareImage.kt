package com.sshevtsov.translator.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sshevtsov.translator.R
import com.sshevtsov.translator.domain.entity.UrlPath

@Composable
fun SquareImage(
  modifier: Modifier = Modifier,
  url: UrlPath
) {
  BoxWithConstraints(
    modifier = modifier
  ) {
    AsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .height(maxWidth),
      model = ImageRequest.Builder(LocalContext.current)
        .data(url.value)
        .crossfade(true)
        .build(),
      contentDescription = "image",
      contentScale = ContentScale.Crop,
      placeholder = painterResource(id = R.drawable.img_image_placeholder_32),
      error = painterResource(id = R.drawable.img_image_error_32)
    )
  }
}