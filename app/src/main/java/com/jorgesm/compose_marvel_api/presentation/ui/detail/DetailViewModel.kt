package com.jorgesm.compose_marvel_api.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.compose_marvel_api.utils.getOneCharacterDetails
import com.jorgesm.domain.model.Character
import com.jorgesm.usecases.GetCharacterByIdUseCase
import com.jorgesm.usecases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase

) : ViewModel() {

    private val _characterDetail = MutableStateFlow<Character>(getOneCharacterDetails())
    val characterDetail: StateFlow<Character> get() = _characterDetail

    fun getCharacterById(itemId: String) {

        try {
            viewModelScope.launch {
                getCharacterById.invoke(itemId).let {
                    _characterDetail.emit(it.result.first())
                    Log.i("Yo", it.toString())
                }
            }
        } catch (e: Exception) {
            Log.e("Yo", e.toString())
        }
    }

}