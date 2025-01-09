package com.jorgesm.data.server.repositoryImpl


import com.jorgesm.data.mappers.transformToDomain
import com.jorgesm.data.server.api.ApiServices
import javax.inject.Inject

class ApiServicesRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    com.jorgesm.domain.repositoy.RemoteRepository {

    override suspend fun getAllCharacters(offset: String): com.jorgesm.domain.model.response.CharactersResponse {
        var result: List<com.jorgesm.domain.model.Character> = listOf()
        apiServices.getCharacterList(  offset = offset).data?.results?.let {
            result = it.transformToDomain()
        }
        return com.jorgesm.domain.model.response.CharactersResponse(result)
    }

    override suspend fun getCharacterById(itemId: String): com.jorgesm.domain.model.response.CharactersResponse {
        var result: List<com.jorgesm.domain.model.Character> = listOf()
        apiServices.geCharacterById(itemId).data?.results?.let { results ->
            result = results.transformToDomain()
        }
        return com.jorgesm.domain.model.response.CharactersResponse(result)
    }
}

