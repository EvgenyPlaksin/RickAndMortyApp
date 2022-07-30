package com.lnight.rickandmortyfacts.presentation.characters_list.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lnight.rickandmortyfacts.common.Screen
import com.lnight.rickandmortyfacts.common.shouldLoadMore
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel
import com.lnight.rickandmortyfacts.presentation.common.RetrySection

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    viewModel: CharactersListViewModel = hiltViewModel(),
    navController: NavController,
    isCompactScreen: Boolean = true
) {

    val state = viewModel.state.value
    val gridState = rememberLazyGridState()

    if (state.isLoading) {
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

    if (state.error.isNotBlank() && state.charactersListEntity == null) {
        RetrySection(
            modifier = Modifier
                .padding(
                    top = if (isCompactScreen) 100.dp else 30.dp,
                    start = if (isCompactScreen) 0.dp else 80.dp,
                    end = if (isCompactScreen) 0.dp else 60.dp
                ),
            text = state.error,
            onClick = {
                viewModel.getCharactersList()
            }
        )
    }
    val previousPage = viewModel.previousPage.value

    LaunchedEffect(key1 = gridState.shouldLoadMore()) {
        val page =
            if (isCompactScreen) {
                if (state.charactersListEntity != null) (gridState.layoutInfo.totalItemsCount) / 10 + 1 else 1
            } else {
                if (state.charactersListEntity != null) (gridState.layoutInfo.totalItemsCount) / 5 + 1 else 1
            }
        if (!state.isLoading && page != previousPage && page != (state.charactersListEntity?.pageInfo?.pages?.plus(
                1
            )
                ?: 0)
        ) {
            Log.e(
                "TAG",
                "shouldLoadMore -> $page, ${gridState.layoutInfo.totalItemsCount}, $previousPage"
            )
            viewModel.getCharactersList(page)
            viewModel.previousPage.value = page
        }
    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        state = gridState,
        columns = if (isCompactScreen) GridCells.Fixed(2) else GridCells.Fixed(4)
    ) {

        if (viewModel.searchedList.value.isNotEmpty()) {
            items(viewModel.searchedList.value) { character ->
                CharacterItem(
                    entry = character,
                    onClick = {
                        navController.navigate(Screen.DetailScreen.route + "/${character.id}")
                    },
                    modifier = Modifier.padding(
                        start = if (isCompactScreen) 6.dp else 15.dp,
                        top = if (isCompactScreen) 6.dp else 15.dp
                    ),
                    isCompactScreen = isCompactScreen
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
                    modifier = Modifier.padding(
                        start = if (isCompactScreen) 6.dp else 15.dp,
                        top = if (isCompactScreen) 6.dp else 15.dp
                    ),
                    isCompactScreen = isCompactScreen
                )

            }

        }
        val page =
            if (isCompactScreen) {
                if (state.charactersListEntity != null) (gridState.layoutInfo.totalItemsCount) / 10 + 1 else 1
            } else {
                if (state.charactersListEntity != null) (gridState.layoutInfo.totalItemsCount) / 5 + 1 else 1
            }

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