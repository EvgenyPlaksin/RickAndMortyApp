package com.lnight.rickandmortyfacts.presentation.charackter_detail.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData

@Composable
fun InfoColumn(
    characterData: DetailCharacterData,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = characterData.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Canvas(modifier = Modifier.size(21.dp)) {
                val color = when(characterData.status) {
                    true -> Color.Green
                    false -> Color.Red
                    null -> Color.LightGray
                }

                drawCircle(color = color, radius = 21f)
            }
            Spacer(modifier = Modifier.width(3.dp))

            val text = when(characterData.status) {
                true -> "Alive"
                false -> "Dead"
                null -> "Unknown"
            }
            Text(
                color = Color.White,
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    color = Color.White,
                    text = "Species",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
                Text(
                    color = Color.White,
                    text = characterData.species,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
            }

            Column {
                Text(
                    color = Color.White,
                    text = "Location",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
                Text(
                    color = Color.White,
                    text = characterData.cityName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
            }

            Column {
                Text(
                    color = Color.White,
                    text = "Gender",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
                Text(
                    color = Color.White,
                    text = characterData.gender,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(110.dp)
                )
            }
        }
    }

}