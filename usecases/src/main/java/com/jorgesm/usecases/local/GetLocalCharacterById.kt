package com.jorgesm.usecases.local

import com.jorgesm.domain.model.Character
import com.jorgesm.domain.repository.LocalRepository
import javax.inject.Inject

class GetLocalCharacterById @Inject constructor(
    private val localRepository: LocalRepository

) {
    suspend operator fun invoke(id: Long): Character =
         localRepository.findCharacterById(id)

}