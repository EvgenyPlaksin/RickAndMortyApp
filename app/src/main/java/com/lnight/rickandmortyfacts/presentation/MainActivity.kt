package com.lnight.rickandmortyfacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lnight.rickandmortyfacts.common.Screen
import com.lnight.rickandmortyfacts.presentation.charackter_detail.components.CharacterDetailScreen
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel
import com.lnight.rickandmortyfacts.presentation.characters_list.components.CharactersListScreen
import com.lnight.rickandmortyfacts.presentation.ui.theme.RickAndMortyFactsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyFactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route
                    ) {
                        composable(Screen.ListScreen.route) {
                            CharactersListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.DetailScreen.route + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            CharacterDetailScreen(it.arguments?.getInt("id", -1) ?: -1)
                        }
                    }
                }
            }
        }
    }
}
