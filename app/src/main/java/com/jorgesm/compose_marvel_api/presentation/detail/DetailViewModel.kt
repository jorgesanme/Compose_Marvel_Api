package com.jorgesm.compose_marvel_api.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.compose_marvel_api.utils.getEmptyCharacterDetails
import com.jorgesm.domain.model.Character
import com.jorgesm.usecases.local.GetLocalCharacterById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLocalCharacterById: GetLocalCharacterById
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
    fun setFavorite(){
        _characterDetail.value.isFavorite = !_characterDetail.value.isFavorite
    }
}