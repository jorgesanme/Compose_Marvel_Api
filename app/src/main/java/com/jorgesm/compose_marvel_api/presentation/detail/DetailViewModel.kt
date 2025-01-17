package com.jorgesm.compose_marvel_api.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.compose_marvel_api.utils.getEmptyCharacterDetails
import com.jorgesm.domain.model.Character
import com.jorgesm.usecases.local.GetLocalCharacterByIdUseCase
import com.jorgesm.usecases.local.UpdateLocalCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLocalCharacterById: GetLocalCharacterByIdUseCase,
    private val updateLocalCharacterUseCase: UpdateLocalCharacterUseCase
) : ViewModel() {

    private val _characterDetail = MutableStateFlow(getEmptyCharacterDetails())
    val characterDetail: StateFlow<Character> get() = _characterDetail

    fun getCharacterById(itemId: Long) {
        viewModelScope.launch {
            getLocalCharacterById(itemId).let {
                _characterDetail.emit(it)
            }
        }
    }

    fun setFavorite(item: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            _characterDetail.value = item.copy(isFavorite = !item.isFavorite)
            updateItem(_characterDetail.value)
        }
    }

    fun setNickName(nickname: String){
        viewModelScope.launch(Dispatchers.IO) {
            _characterDetail.value = _characterDetail.value.copy(nickName =  nickname)
            updateItem(_characterDetail.value)

        }
    }

    private fun updateItem(item: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            async { updateLocalCharacterUseCase(item) }.await()
        }
    }
}