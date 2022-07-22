package com.lnight.rickandmortyfacts.presentation.characters_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.lnight.rickandmortyfacts.domain.model.CharactersData

@Composable
fun CharacterItem(
    entry: CharactersData,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    isCompactScreen: Boolean
) {

    if(isCompactScreen) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(1f)
                .clickable {
                    onClick(entry.id)
                }
        ) {
            Box {
                val request = ImageRequest.Builder(LocalContext.current)
                    .data(entry.image)
                    .crossfade(true)
                    .build()

                SubcomposeAsyncImage(
                    model = request,
                    contentDescription = "character",
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .scale(0.5f)
                        )
                    },
                    modifier = Modifier
                        .size(200.dp)
                        .align(Center)
                )
                Row(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 200f
                            )
                        )
                        .fillMaxSize(),
                    verticalAlignment = Bottom
                ) {
                    Text(
                        text = entry.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    } else {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(1f)
                .clickable {
                    onClick(entry.id)
                }
        ) {
            Box {
                val request = ImageRequest.Builder(LocalContext.current)
                    .data(entry.image)
                    .crossfade(true)
                    .build()

                SubcomposeAsyncImage(
                    model = request,
                    contentDescription = "character",
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .scale(0.3f)
                        )
                    },
                    modifier = Modifier
                        .size(250.dp)
                        .align(Center)
                )
                Row(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 150f
                            )
                        )
                        .fillMaxSize(),
                    verticalAlignment = Bottom
                ) {
                    Text(
                        text = entry.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}