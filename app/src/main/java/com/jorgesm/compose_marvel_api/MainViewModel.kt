package com.jorgesm.compose_marvel_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesm.data.server.repository.ApiServicesRepositoryImpl
import com.jorgesm.domain.model.response.CharactersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiServicesRepositoryImpl: ApiServicesRepositoryImpl
) : ViewModel() {

    private val _list = MutableStateFlow<CharactersResponse>(CharactersResponse(listOf()))
    val list: StateFlow<CharactersResponse> get() = _list

    init {
        getList()
    }
    private fun getList(){
        viewModelScope.launch {
            _list.emit(apiServicesRepositoryImpl.getAllCharacters())
        }
    }

}

