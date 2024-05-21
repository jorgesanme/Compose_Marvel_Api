package com.jorgesm.usecases

import com.jorgesm.data.server.repository.RemoteRepository
import com.jorgesm.domain.model.response.CharactersResponse
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor (private val repository: RemoteRepository) {
    suspend operator fun invoke() : CharactersResponse =
        repository.getAllCharacters()
}