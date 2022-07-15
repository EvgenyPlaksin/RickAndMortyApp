package com.lnight.rickandmortyfacts.presentation.characters_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.rickandmortyfacts.domain.use_case.characters_list.CharactersListUseCase
import com.lnight.rickandmortyfacts.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersListUseCase: CharactersListUseCase
): ViewModel() {

    private val _state = mutableStateOf<CharactersListState>(CharactersListState())
    val state: State<CharactersListState> = _state

    init {
        getCharactersList()
    }

     fun getCharactersList(page: Int = 1) {
        charactersListUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharactersListState(charactersListEntity = result.data)
                }
                is Resource.Loading -> {
                    _state.value = CharactersListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CharactersListState(error = result.message ?: "Unknown error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}