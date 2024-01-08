package viewmodel

import entity.BirdPictureEntity

data class BirdsUiState(
    val pictures: List<BirdPictureEntity> = emptyList(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false,
    val error: Error? = null
) {
    val categories = pictures.map { it.category }.toSet()
    val selectedPictures = pictures.filter { it.category == selectedCategory }
}
