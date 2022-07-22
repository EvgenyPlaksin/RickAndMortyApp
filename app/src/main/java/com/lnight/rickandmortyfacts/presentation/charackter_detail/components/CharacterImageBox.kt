package com.lnight.rickandmortyfacts.presentation.charackter_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.lnight.rickandmortyfacts.presentation.charackter_detail.CharacterDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun CharacterImageBox(
    image: String,
    name: String,
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    imageSize: Dp = 300.dp
) {

    var dominantColor by remember {
        mutableStateOf(Color.Black)
    }
    val gradient = Brush.verticalGradient(
        listOf(
            Color.Transparent,
            dominantColor
        ),
        endY = 1000f
    )

    val request = ImageRequest.Builder(LocalContext.current)
        .data(image)
        .crossfade(true)
        .build()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true){
        scope.launch {
            val result = request.context.imageLoader.execute(request).drawable!!
            viewModel.calcDominantColor(result) { color ->
                dominantColor = color
            }
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(gradient)
            .clickable { }
    ) {
            SubcomposeAsyncImage(
                model = request,
                contentDescription = name,
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .scale(0.5f)
                    )
                },
                modifier = Modifier
                    .size(imageSize)
                    .padding(all = if(imageSize != 300.dp) 16.dp else 0.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(8.dp))
            )
    }

}