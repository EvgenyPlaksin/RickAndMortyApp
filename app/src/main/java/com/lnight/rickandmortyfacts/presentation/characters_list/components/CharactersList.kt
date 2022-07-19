package com.lnight.rickandmortyfacts.presentation.characters_list.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lnight.rickandmortyfacts.common.Screen
import com.lnight.rickandmortyfacts.common.shouldLoadMore
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    viewModel: CharactersListViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val listState = rememberLazyListState()

    if(state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = MaterialTheme.colors.primary
            )
        }
    }

    if(state.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.error,
                fontSize = 25.sp,
                color = MaterialTheme.colors.error
            )
        }
    }
    val previousPage = viewModel.previousPage.value

    LaunchedEffect(key1 = listState.shouldLoadMore()) {
        val page = if(state.charactersListEntity != null) (listState.layoutInfo.totalItemsCount) / 10 + 1 else 1
        if(!state.isLoading && page != previousPage && page != (state.charactersListEntity?.pageInfo?.pages?.plus(
                1
            )
                ?: 0)
        ) {
            Log.e("TAG", "shouldLoadMore -> $page, ${listState.layoutInfo.totalItemsCount}, $previousPage")
            viewModel.getCharactersList(page)
            viewModel.previousPage.value = page
        }
    }
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            state = listState,
            cells = GridCells.Fixed(2)
        ) {

            if(viewModel.searchedList.value.isNotEmpty()) {
                items(viewModel.searchedList.value) { character ->
                    CharacterItem(
                        entry = character,
                        onClick = {
                            navController.navigate(Screen.DetailScreen.route + "/${character.id}")
                        },
                        modifier = Modifier.padding(start = 6.dp, top = 6.dp)
                    )
                }
            }

            if (state.charactersListEntity != null && viewModel.searchedList.value.isEmpty() && !viewModel.isNullResult) {
                items(state.charactersListEntity.charactersData) { character ->

                    CharacterItem(
                        entry = character,
                        onClick = {
                            navController.navigate(Screen.DetailScreen.route + "/${character.id}")
                        },
                        modifier = Modifier.padding(start = 6.dp, top = 6.dp)
                    )
                }

            }
            val page =
                if (state.charactersListEntity != null) (listState.layoutInfo.totalItemsCount) / 10 + 1 else 1
            if (page == (state.charactersListEntity?.pageInfo?.pages
                    ?: 0)
            ) {
                item {
                    Text(
                        text = "The end",
                        fontSize = 25.sp
                    )
                }
            }
        }

}