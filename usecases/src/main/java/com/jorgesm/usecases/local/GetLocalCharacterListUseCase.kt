package com.jorgesm.usecases.local

import com.jorgesm.domain.model.Character
import com.jorgesm.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalCharacterListUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(): Flow<List<Character>> = localRepository.getAllCharacters()
}