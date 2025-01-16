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
    private val getLocalDataCountUseCase: GetLocalDataCountUseCase
) : ViewModel() {
    companion object {
        const val NUMBER_OFFSET_PAGE = 5
    }

    private val _list = MutableStateFlow(CharactersResponse(listOf()))
    val list: StateFlow<CharactersResponse> get() = _list

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _offset = MutableStateFlow(NUMBER_OFFSET_PAGE)

    private val _initList = MutableStateFlow(0)

    private val _isPreviousArrowEnable = MutableStateFlow<Boolean>(false)
    val isPreviousArrowEnable: StateFlow<Boolean> = _isPreviousArrowEnable


     fun getRemoteCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit(true)
            getCharactersListUseCase(_offset.value.toString()).result.let {
                saveCharacterInDataBaseUseCase.invoke(it)
            }
            _isLoading.emit(false)
        }
    }

    fun getLocalDataList() {
        viewModelScope.launch {
            getLocalCharacterListUseCase(_initList.value + 1, _offset.value).collect {
                _list.emit(it)
            }
        }
    }

    fun searchNextList() {
        viewModelScope.launch {
            _initList.emit(_initList.value + NUMBER_OFFSET_PAGE)
            _offset.emit(_offset.value + NUMBER_OFFSET_PAGE)
            _isPreviousArrowEnable.emit(true)
            if (getLocalDataCountUseCase() == _offset.value)
                getRemoteCharacterList()
            else
                getLocalDataList()
        }
    }

    fun searchPreviousList() {
        viewModelScope.launch {
            val checkDataBase = checkFirstRowInDDBB(getLocalDataCountUseCase(), _initList.value)
            if (checkDataBase) {
                if (isPossibleToGetPreviousList(_initList.value, _offset.value)) {
                    _initList.emit(_initList.value - NUMBER_OFFSET_PAGE)
                    _offset.emit(_offset.value - NUMBER_OFFSET_PAGE)
                    getLocalDataList()
                }
            }
        }
    }

    private fun isPossibleToGetPreviousList(initList: Int, offset: Int): Boolean {
        val isPossible = (initList >= NUMBER_OFFSET_PAGE && offset >= NUMBER_OFFSET_PAGE)
        viewModelScope.launch { _isPreviousArrowEnable.emit(isPossible) }
        return isPossible
    }

    private fun checkFirstRowInDDBB(dbCount: Int, initList: Int): Boolean {
        val dbInitPosition = (dbCount - NUMBER_OFFSET_PAGE)
        return dbInitPosition > initList
    }
}