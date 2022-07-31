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
import com.lnight.rickandmortyfacts.domain.use_case.add_character.AddCharacterUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_cashed_list.GetCashedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersListUseCase: CharactersListUseCase,
    private val getCashedListUseCase: GetCashedListUseCase,
    private val addCharacterUseCase: AddCharacterUseCase
): ViewModel() {

    private val _state = mutableStateOf<CharactersListState>(CharactersListState())
    val state: State<CharactersListState> = _state

    private val _searchedList = mutableStateOf<List<CharactersData>>(listOf())
    val searchedList: State<List<CharactersData>> = _searchedList

    private val _cashedList = mutableStateOf<List<CharactersData>>(listOf())
    val cashedList: State<List<CharactersData>> = _cashedList

    var isNullResult = false

    val previousPage = mutableStateOf<Int>(0)

    val searchText = mutableStateOf<String>("")

    val isHintDisplayed = mutableStateOf<Boolean>(searchText.value.isBlank())

    private var getCharactersJob: Job? = null

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
            try {
                _searchedList.value = cashedList.value.filter { charactersData ->
                    charactersData.name.contains(query, ignoreCase = true)
                }
            } catch (e: NullPointerException) {
                return
            }
        }
        Log.e("TAG", "searchList -> ${searchedList.value}")
        isNullResult = _searchedList.value.isEmpty()
    }

     fun getCharactersList(page: Int = 1) {
        charactersListUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                     if(result.data != null) {
                         previousPage.value = page
                         if(cashedList.value.isEmpty()) {
                             _state.value = state.value.copy(
                                 charactersListEntity = CharactersListEntity(
                                     pageInfo = result.data.pageInfo,
                                     charactersData = state.value.charactersListEntity?.charactersData?.plus(
                                         result.data.charactersData
                                     ) ?: result.data.charactersData
                                 ),
                                 isLoading = false
                             )
                             _state.value.charactersListEntity?.charactersData?.forEach { character ->
                                 addCharacter(character)
                             }
                         } else {
                             _cashedList.value = cashedList.value + result.data.charactersData

                             result.data.charactersData.forEach { character ->
                                 addCharacter(character)
                             }
                         }
                     } else {
                         _state.value = state.value.copy(isLoading = false)
                     }
                }
                is Resource.Loading -> {
                    if(_state.value.charactersListEntity == null) {
                        _state.value = CharactersListState(isLoading = cashedList.value.isEmpty())
                    }
                }
                is Resource.Error -> {
                    getCachedData()
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown error occurred"
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getCachedData() {
        if(cashedList.value.isEmpty()) {
            getCharactersJob?.cancel()
            getCharactersJob = getCashedListUseCase().onEach { result ->
                _cashedList.value = result
            }.launchIn(viewModelScope)
        }
    }

    private fun addCharacter(charactersData: CharactersData) {
        viewModelScope.launch {
            addCharacterUseCase(charactersData)
        }
    }

}