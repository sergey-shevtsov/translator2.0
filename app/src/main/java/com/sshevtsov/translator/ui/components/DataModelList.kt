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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
    items(list) { item ->
      DataModelExpandableItem(
        modifier = Modifier.shadow(elevation = 2.dp),
        dataModel = item,
        isExpanded = expandedIds.contains(item.id),
        onExpandClick = { onExpandClick(item.id) },
        onMeaningClick = onMeaningClick
      )
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
  }
}
