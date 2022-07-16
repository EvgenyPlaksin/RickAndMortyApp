package com.lnight.rickandmortyfacts.presentation.characters_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lnight.rickandmortyfacts.R

@Composable
fun CharactersListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Rick and Morty",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
        )
        SearchBar(
            hint = "Search...",
            modifier = Modifier.fillMaxWidth()
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        CharactersList()
    }

}