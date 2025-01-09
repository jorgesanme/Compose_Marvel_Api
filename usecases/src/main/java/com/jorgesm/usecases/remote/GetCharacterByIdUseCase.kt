package com.jorgesm.usecases.remote

import com.jorgesm.domain.repositoy.RemoteRepository
import com.jorgesm.domain.model.response.CharactersResponse
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: RemoteRepository) {

    suspend operator fun invoke(itemId: String): CharactersResponse = repository.getCharacterById(itemId)
}