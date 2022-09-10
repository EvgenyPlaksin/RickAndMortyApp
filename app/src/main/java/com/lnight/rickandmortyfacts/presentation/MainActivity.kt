package com.lnight.rickandmortyfacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lnight.rickandmortyfacts.common.Screen
import com.lnight.rickandmortyfacts.presentation.charackter_detail.components.CharacterDetailScreen
import com.lnight.rickandmortyfacts.presentation.characters_list.components.CharactersListScreen
import com.lnight.rickandmortyfacts.presentation.ui.theme.RickAndMortyFactsTheme

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
                    val windowInfo = rememberWindowInfo()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route
                    ) {
                        composable(Screen.ListScreen.route) {
                            if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                                CharactersListScreen(navController = navController)
                            } else {
                                CharactersListScreen(
                                    navController = navController,
                                    isCompactScreen = false
                                )
                            }
                        }
                        composable(
                            route = Screen.DetailScreen.route + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { backStackEntry ->
                            if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                                val id = backStackEntry.arguments?.getInt("id")
                                CharacterDetailScreen(id = id)
                            } else {
                                CharacterDetailScreen(isCompactScreen = false, id = id)
                            }
                        }
                    }

                }
            }
        }
    }
}
