package com.jorgesm.compose_marvel_api.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.compose_marvel_api.utils.getEmptyCharacterDetails
import com.jorgesm.domain.model.Character
import com.jorgesm.usecases.remote.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase

) : ViewModel() {

    private val _characterDetail = MutableStateFlow(getEmptyCharacterDetails())
    val characterDetail: StateFlow<Character> get() = _characterDetail

    fun getCharacterById(itemId: String) {
        viewModelScope.launch {
            getCharacterById.invoke(itemId).let {
                _characterDetail.emit(it.result.first())
            }
        }
    }
    fun setFavorite(){
        _characterDetail.value.isFavorite = !_characterDetail.value.isFavorite
    }
}