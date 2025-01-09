package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.usecases.local.GetLocalCharacterListUseCase
import com.jorgesm.usecases.local.SaveCharacterInDataBaseUseCase
import com.jorgesm.usecases.remote.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val getLocalCharacterListUseCase: GetLocalCharacterListUseCase,
    private val saveCharacterInDataBaseUseCase: SaveCharacterInDataBaseUseCase,
) : ViewModel() {
    companion object{
        const val NUMBER_OFFSET_PAGE = 10
    }

    private val _list = MutableStateFlow(CharactersResponse(listOf()))
    val list: StateFlow<CharactersResponse> get() = _list
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _offset = MutableStateFlow(0)
    val offset: StateFlow<Int> get() = _offset

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit(true)
            getCharactersListUseCase.invoke(_offset.toString()).result.let {
                saveCharacterInDataBaseUseCase.invoke(it)
            }
            _isLoading.emit(false)
        }
    }

    fun getList() {
        viewModelScope.launch {
            getLocalCharacterListUseCase.invoke().collect {
                _list.emit(it)
            }
        }
    }

    fun setNewOffset() {
        viewModelScope.launch {
            _offset.emit(_offset.value + NUMBER_OFFSET_PAGE)
        }
    }
}