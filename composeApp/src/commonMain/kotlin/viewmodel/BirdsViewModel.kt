package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.BirdApi

class BirdsViewModel : ViewModel() {
    private val birdApi = BirdApi()

    private val _uiState = MutableStateFlow(BirdsUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    fun loadPictures() {
        viewModelScope.launch {
            val pictures = birdApi.getBirdPictures()
            _uiState.update { state ->
                state.copy(pictures = pictures)
            }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update { state ->
            state.copy(
                selectedCategory = category
                    .takeUnless { state.selectedCategory == category }
            )
        }
    }
}
