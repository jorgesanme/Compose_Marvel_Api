package com.jorgesm.casodeuso.remote

import com.jorgesm.domain.repository.RemoteRepository
import com.jorgesm.domain.model.response.CharactersResponse
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor (private val repository: RemoteRepository) {
    suspend operator fun invoke(offset: String) : CharactersResponse =
        repository.getAllCharacters(offset = offset)
}