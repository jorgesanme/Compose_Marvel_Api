package com.jorgesm.usecases.local

import com.jorgesm.domain.repositoy.LocalRepository
import javax.inject.Inject

class GetLocalDataCountUseCase @Inject constructor(
    private val localRepository: LocalRepository
)  {
    suspend operator fun invoke() = localRepository.countCharacter()
}