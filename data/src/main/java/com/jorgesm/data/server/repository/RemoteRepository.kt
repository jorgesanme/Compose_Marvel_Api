package com.jorgesm.data.server.repository

import com.jorgesm.data.models.CharacterResponseDTO
import com.jorgesm.domain.model.response.CharactersResponse

interface RemoteRepository {
    suspend fun getAllCharacters(): CharactersResponse
}