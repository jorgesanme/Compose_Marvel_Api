package com.jorgesm.data.server.repository

import com.jorgesm.data.mappers.transformToDomain
import com.jorgesm.data.server.api.ApiServices
import com.jorgesm.domain.model.Character
import com.jorgesm.domain.model.response.CharactersResponse
import javax.inject.Inject

class ApiServicesRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    RemoteRepository {

    override suspend fun getAllCharacters(): CharactersResponse {
        var result: List<Character> = listOf()
        apiServices.getCharacterList().data?.results?.let {
            result = it.transformToDomain()

        }

        return CharactersResponse(result)
    }

}