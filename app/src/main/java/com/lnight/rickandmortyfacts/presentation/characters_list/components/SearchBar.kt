package com.lnight.rickandmortyfacts.presentation.characters_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    viewModel: CharactersListViewModel = hiltViewModel()
) {
    val text = viewModel.searchText.value

    val isHintDisplayed = viewModel.isHintDisplayed.value

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                viewModel.searchText.value = it
                onSearch(it)
            },
            textStyle = LocalTextStyle.current.copy(color = Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    viewModel.isHintDisplayed.value = if(viewModel.searchText.value.isBlank()) it.isFocused else true
                }
        )
        if(!isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}