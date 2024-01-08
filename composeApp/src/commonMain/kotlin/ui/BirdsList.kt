package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import viewmodel.BirdsUiState

private const val CONTENT_PADDING_DP = 8
private const val PICTURE_SIZE_DP = 180
private const val CATEGORY_BORDER_DP = 2
private const val CATEGORY_ELEVATION_DP = 4
private const val CATEGORY_ASPECT_RATIO = 1.0f
private const val CATEGORY_WEIGHT = 1.0f
private const val PICTURE_ASPECT_RATIO = 1.0f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BirdsList(
    uiState: BirdsUiState,
    onSelectCategory: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CONTENT_PADDING_DP.dp),
            horizontalArrangement = Arrangement.spacedBy(CONTENT_PADDING_DP.dp),
        ) {
            for (category in uiState.categories) {
                Card(
                    modifier = Modifier
                        .aspectRatio(CATEGORY_ASPECT_RATIO)
                        .weight(CATEGORY_WEIGHT),
                    elevation = CATEGORY_ELEVATION_DP.dp,
                    onClick = { onSelectCategory(category) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.LightGray)
                            .then(
                                if (uiState.selectedCategory == category) {
                                    Modifier.border(CATEGORY_BORDER_DP.dp, Color.Black)
                                } else {
                                    Modifier
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = uiState.selectedPictures.isNotEmpty()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(PICTURE_SIZE_DP.dp),
                horizontalArrangement = Arrangement.spacedBy(CONTENT_PADDING_DP.dp),
                verticalArrangement = Arrangement.spacedBy(CONTENT_PADDING_DP.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = CONTENT_PADDING_DP.dp),
            ) {
                items(uiState.selectedPictures) { item ->
                    KamelImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(PICTURE_ASPECT_RATIO),
                        resource = asyncPainterResource(item.pictureUrl),
                        contentDescription = item.pictureDescription,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}