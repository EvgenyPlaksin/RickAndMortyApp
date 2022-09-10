package com.lnight.rickandmortyfacts.presentation.characters_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lnight.rickandmortyfacts.R
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersListScreen(
    viewModel: CharactersListViewModel = getViewModel(),
    navController: NavController,
    isCompactScreen: Boolean = true
) {

    if(isCompactScreen) {
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
                modifier = Modifier.fillMaxWidth(),
                onSearch = {
                    viewModel.onSearch(it)
                })
            Spacer(modifier = Modifier.height(16.dp))
            CharactersList(navController = navController)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Rick and Morty",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                onSearch = {
                    viewModel.onSearch(it)
                })
            Spacer(modifier = Modifier.height(20.dp))
            CharactersList(
                navController = navController,
                isCompactScreen = false
            )
        }
    }
}