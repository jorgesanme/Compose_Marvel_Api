package com.jorgesm.usecases.local

import com.jorgesm.domain.model.Character
import com.jorgesm.domain.repositoy.LocalRepository
import javax.inject.Inject

class GetLocalCharacterByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(id: Long): Character =
         localRepository.findCharacterById(id)
}