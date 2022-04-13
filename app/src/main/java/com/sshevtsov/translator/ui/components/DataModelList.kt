package com.sshevtsov.translator.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sshevtsov.translator.R
import com.sshevtsov.translator.ui.entity.UiDataModel
import com.sshevtsov.translator.ui.entity.UiMeaning

@Composable
fun DataModelList(
  modifier: Modifier = Modifier,
  list: List<UiDataModel>,
  expandedIds: Set<UiDataModel.Id>,
  onExpandClick: (UiDataModel.Id) -> Unit,
  onMeaningClick: (UiMeaning.Id) -> Unit
) {
  LazyColumn(
    modifier = modifier
  ) {
    list.forEachIndexed { index, dataModel ->
      item {
        Divider()
        DataModelExpandableItem(
          dataModel = dataModel,
          isExpanded = expandedIds.contains(dataModel.id),
          onExpandClick = { onExpandClick(dataModel.id) },
          onMeaningClick = onMeaningClick
        )
        if (index == list.lastIndex) {
          Divider()
        }
      }
    }
  }
}

@Composable
fun DataModelExpandableItem(
  modifier: Modifier = Modifier,
  dataModel: UiDataModel,
  isExpanded: Boolean = false,
  onExpandClick: () -> Unit,
  onMeaningClick: (UiMeaning.Id) -> Unit
) {
  val iconAngleDegrees by animateFloatAsState(
    targetValue = if (isExpanded) 180f else 0f,
    animationSpec = tween(
      durationMillis = 100
    )
  )
  Column(
    modifier = modifier
      .animateContentSize()
  ) {
    Row(
      modifier = Modifier
        .clickable(onClick = onExpandClick)
        .fillMaxWidth()
        .heightIn(min = 64.dp)
        .padding(all = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = dataModel.text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.width(16.dp))
      Icon(
        modifier = Modifier.rotate(iconAngleDegrees),
        painter = painterResource(id = R.drawable.ic_chevron_down_24),
        contentDescription = "Chevron icon"
      )
    }
    if (isExpanded) {
      dataModel.meanings.forEach { meaning ->
        MeaningItem(
          meaning = meaning,
          onClick = { onMeaningClick(meaning.id) }
        )
      }
    }
  }
}

@Composable
fun MeaningItem(
  modifier: Modifier = Modifier,
  meaning: UiMeaning,
  onClick: () -> Unit
) {
  Row(
    modifier = modifier
      .clickable(onClick = onClick)
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    AsyncImage(
      modifier = Modifier
        .size(32.dp)
        .clip(RoundedCornerShape(8.dp)),
      model = ImageRequest.Builder(LocalContext.current)
        .data(meaning.previewUrl?.value)
        .crossfade(true)
        .build(),
      contentDescription = "meaning image",
      contentScale = ContentScale.Crop,
      placeholder = painterResource(id = R.drawable.img_image_placeholder_32),
      error = painterResource(id = R.drawable.img_image_error_32)
    )
    Text(
      text = meaning.translation.text,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
  }
}
