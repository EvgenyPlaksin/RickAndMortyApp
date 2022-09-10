package com.lnight.rickandmortyfacts.presentation.charackter_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lnight.rickandmortyfacts.presentation.charackter_detail.CharacterDetailViewModel
import com.lnight.rickandmortyfacts.presentation.common.RetrySection
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = getViewModel(),
    isCompactScreen: Boolean = true,
    id: Int?
) {
    LaunchedEffect(key1 = true) {
        viewModel.getCharacterData(id)
    }
    val state = viewModel.state.value
    val locationState = viewModel.locationState.value

    if(state.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RetrySection(
                text = state.error,
                onClick = {
                    viewModel.getCharacterData(id)
                }
            )
        }

    }

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

    if(state.characterData != null) {

        if(isCompactScreen) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                CharacterImageBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(16.dp),
                    image = state.characterData.image,
                    name = state.characterData.name
                )
                Spacer(modifier = Modifier.height(6.dp))
                InfoColumn(
                    characterData = state.characterData,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if(locationState.locationData != null) {
                        items(locationState.locationData.charactersUrl) {
                            Text(
                                text = it,
                                fontSize = 25.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                CharacterImageBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .padding(horizontal = 30.dp),
                    image = state.characterData.image,
                    name = state.characterData.name,
                    imageSize = 210.dp
                )
                Spacer(modifier = Modifier.height(6.dp))
                InfoColumn(
                    characterData = state.characterData,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}