package com.jorgesm.compose_marvel_api.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.compose_marvel_api.utils.getEmptyCharacterDetails
import com.jorgesm.domain.model.Character
import com.jorgesm.casodeuso.local.GetLocalCharacterByIdUseCase
import com.jorgesm.casodeuso.local.UpdateLocalCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLocalCharacterById: com.jorgesm.casodeuso.local.GetLocalCharacterByIdUseCase,
    private val updateLocalCharacterUseCase: com.jorgesm.casodeuso.local.UpdateLocalCharacterUseCase
) : ViewModel() {

    private val _characterDetail = MutableStateFlow(getEmptyCharacterDetails())
    val characterDetail: StateFlow<Character> get() = _characterDetail

    fun getCharacterById(itemId: Long) {
        viewModelScope.launch {
            getLocalCharacterById.invoke(itemId).let {
                _characterDetail.emit(it)
            }
        }
    }

    fun setFavorite(item: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            _characterDetail.emit(item.copy(isFavorite = !item.isFavorite))
            updateLocalCharacterUseCase.invoke(_characterDetail.value)
        }
    }

    fun setNickName(item: Character, nickname: String){
        viewModelScope.launch(Dispatchers.IO) {
           async { _characterDetail.emit(item.copy(nickName = nickname))}.await()
            updateLocalCharacterUseCase.invoke(_characterDetail.value)
        }
    }
}