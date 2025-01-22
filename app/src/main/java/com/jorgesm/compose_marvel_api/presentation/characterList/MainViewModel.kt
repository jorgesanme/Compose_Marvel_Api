package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.domain.model.Character
import com.jorgesm.usecases.local.GetLocalCharacterListUseCase
import com.jorgesm.usecases.local.GetLocalDataCountUseCase
import com.jorgesm.usecases.local.SaveCharacterInDataBaseUseCase
import com.jorgesm.usecases.remote.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val getLocalCharacterListUseCase: GetLocalCharacterListUseCase,
    private val saveCharacterInDataBaseUseCase: SaveCharacterInDataBaseUseCase,
    private val getLocalDataCountUseCase: GetLocalDataCountUseCase
) : ViewModel() {

    companion object {
        const val NUMBER_OFFSET_PAGE = 20
        const val STEP_ITEM_IN_LIST = 5
    }

    private val _list = MutableStateFlow<List<Character>>((listOf()))
    val list: StateFlow<List<Character>> get() = _list

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _offset = MutableStateFlow(0)

    private val _startSubList = MutableStateFlow(0)
    private val _endSubList = MutableStateFlow(STEP_ITEM_IN_LIST)

    private val _isPreviousArrowEnable = MutableStateFlow(false)
    val isPreviousArrowEnable: StateFlow<Boolean> = _isPreviousArrowEnable

    private val _dbCounter = MutableStateFlow(0)
    val dbCounter: StateFlow<Int> = _dbCounter


    private fun getRemoteCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit(true)
            saveCharacterInDataBaseUseCase(getCharactersListUseCase(_offset.value.toString()).result)
            getLocalDataList()
            _isLoading.emit(false)
        }
        getDDBBCount()
    }

    fun getLocalDataList() {
        getDDBBCount()
        viewModelScope.launch(Dispatchers.IO) {
            getLocalCharacterListUseCase().collect {
                if (it.isEmpty())
                    getRemoteCharacterList()
                else if(_dbCounter.value == _startSubList.value) {
                    getRemoteCharacterList()
                }else
                    _list.value = it.subList(_startSubList.value, _endSubList.value)
            }
        }
    }

    fun searchNextList() {
        viewModelScope.launch {
            _isPreviousArrowEnable.value = true
            if (_dbCounter.value == _endSubList.value) {
                _offset.value += NUMBER_OFFSET_PAGE
                increaseListLimit()
                getLocalDataList()
            } else {
                increaseListLimit()
                getLocalDataList()
            }
        }
    }

    fun searchPreviousList() {
        viewModelScope.launch {
            val checkDataBase = isFirstRowInDDBB(_dbCounter.value, _startSubList.value)
            if (checkDataBase) {
                if (isPossibleToGetPreviousList(_startSubList.value, _endSubList.value)) {
                    decreaseListLimit()
                    getLocalDataList()
                }
            }
        }
    }

    private fun increaseListLimit(){
        _startSubList.value += STEP_ITEM_IN_LIST
        _endSubList.value += STEP_ITEM_IN_LIST
    }

    private fun decreaseListLimit(){
        _startSubList.value -= STEP_ITEM_IN_LIST
        _endSubList.value -= STEP_ITEM_IN_LIST
    }

    private fun isPossibleToGetPreviousList(initList: Int, endList: Int): Boolean {
        val isPossible = (initList >= STEP_ITEM_IN_LIST && endList >= STEP_ITEM_IN_LIST)
        viewModelScope.launch { _isPreviousArrowEnable.emit(isPossible) }
        return isPossible
    }

    private fun isFirstRowInDDBB(dbCount: Int, initList: Int): Boolean {
        return dbCount > initList
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun countAllRegistersInDB(): Int = suspendCancellableCoroutine { continuation ->
        viewModelScope.launch(Dispatchers.IO) {
            val result = getLocalDataCountUseCase()
            continuation.resume(result) { cause: Throwable ->
                continuation.resumeWithException(cause)
            }
        }

    }

    fun getDDBBCount() {
        viewModelScope.launch(Dispatchers.IO) {
            _dbCounter.value =  async { countAllRegistersInDB()}.await()
        }
    }
}