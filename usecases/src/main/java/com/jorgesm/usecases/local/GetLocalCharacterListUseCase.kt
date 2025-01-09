package com.jorgesm.usecases.local

import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.domain.repositoy.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCharacterListUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Flow<CharactersResponse> = localRepository.getAllCharacters()
}