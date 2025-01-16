package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.usecases.local.GetLocalCharacterListUseCase
import com.jorgesm.usecases.local.GetLocalDataCountUseCase
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
    private val getLocalDataCount: GetLocalDataCountUseCase
) : ViewModel() {
    companion object {
        const val NUMBER_OFFSET_PAGE = 5
    }

    private val _list = MutableStateFlow(CharactersResponse(listOf()))
    val list: StateFlow<CharactersResponse> get() = _list

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _offset = MutableStateFlow(NUMBER_OFFSET_PAGE)
    val offset: StateFlow<Int> get() = _offset

    private val _initList = MutableStateFlow(0)
    val initList: StateFlow<Int> get() = _initList


    init {
        getRemoteCharacterList()
    }

    private fun getRemoteCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit(true)
            getCharactersListUseCase.invoke(_offset.value.toString()).result.let {
                saveCharacterInDataBaseUseCase.invoke(it)
            }
            _isLoading.emit(false)
        }
    }

    fun getLocalDataList() {
        viewModelScope.launch {
            getLocalCharacterListUseCase.invoke(_initList.value + 1, _offset.value).collect {
                _list.emit(it)
            }
        }
    }

    fun setNewOffset() {
        viewModelScope.launch {
            _initList.emit(_initList.value + NUMBER_OFFSET_PAGE)
            _offset.emit(_offset.value + NUMBER_OFFSET_PAGE)
            if (getLocalDataCount.invoke() == _offset.value)
                getRemoteCharacterList()
            else
                getLocalDataList()
        }
    }
}