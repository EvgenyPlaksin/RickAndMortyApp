package com.lnight.rickandmortyfacts.presentation.characters_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.model.CharactersListEntity
import com.lnight.rickandmortyfacts.domain.use_case.characters_list.CharactersListUseCase
import com.lnight.rickandmortyfacts.common.Resource
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

    private val _searchedList = mutableStateOf<List<CharactersData>>(listOf())
    val searchedList: State<List<CharactersData>> = _searchedList

    var isNullResult = false

    val previousPage = mutableStateOf<Int>(0)

    val searchText = mutableStateOf<String>("")

    val isHintDisplayed = mutableStateOf<Boolean>(searchText.value.isBlank())

    fun onSearch(query: String) {
        Log.e("TAG", "onSearch -> $query")
        if(query.isEmpty()) {
            _searchedList.value = emptyList()
            isNullResult = false
            return
        }
        try {
            _searchedList.value = state.value.charactersListEntity!!.charactersData.filter { charactersData ->
                charactersData.name.contains(query, ignoreCase = true)
            }
        } catch (e: NullPointerException) {
           return
        }
        isNullResult = _searchedList.value.isEmpty()
    }

     fun getCharactersList(page: Int = 1) {
        charactersListUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                     if(result.data != null) {
                         _state.value = state.value.copy(charactersListEntity = CharactersListEntity(
                                 pageInfo = result.data.pageInfo,
                                 charactersData = state.value.charactersListEntity?.charactersData?.plus(
                                     result.data.charactersData
                                 ) ?: result.data.charactersData
                             ),
                             isLoading = false
                         )
                     } else {
                         _state.value = state.value.copy(isLoading = false)
                     }
                }
                is Resource.Loading -> {
                    if(_state.value.charactersListEntity == null) {
                        _state.value = CharactersListState(isLoading = true)
                    }
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(isLoading = false, error = result.message ?: "Unknown error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}