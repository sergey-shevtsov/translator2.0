package com.sshevtsov.translator.ui.components

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchTextField(
  modifier: Modifier = Modifier,
  currentQuery: String,
  onQueryChanged: (String) -> Unit,
  label: String? = null,
  onSearch: KeyboardActionScope.() -> Unit
) {
  OutlinedTextField(
    modifier = modifier,
    value = currentQuery,
    onValueChange = onQueryChanged,
    singleLine = true,
    label = {
      if (label != null) {
        Text(text = label)
      }
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = onSearch)
  )
}
