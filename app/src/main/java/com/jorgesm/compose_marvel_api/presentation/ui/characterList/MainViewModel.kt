package com.jorgesm.compose_marvel_api.presentation.ui.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.usecases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _list = MutableStateFlow<CharactersResponse>(CharactersResponse(listOf()))
    val list: StateFlow<CharactersResponse> get() = _list

    fun getList(offset: Int){
        viewModelScope.launch {
            _list.emit(getCharactersListUseCase.invoke(offset.toString()))
        }
    }
}

