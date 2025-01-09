package com.jorgesm.usecases.remote

import com.jorgesm.domain.repositoy.RemoteRepository
import com.jorgesm.domain.model.response.CharactersResponse
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor (private val repository: RemoteRepository) {
    suspend operator fun invoke(offset: String) : CharactersResponse =
        repository.getAllCharacters(offset = offset)
}