package com.jorgesm.data.server.repositoryImpl


import com.jorgesm.data.mappers.transformToDomain
import com.jorgesm.data.server.api.ApiServices
import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.domain.repositoy.RemoteRepository
import javax.inject.Inject

class ApiServicesRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
):RemoteRepository {
    override suspend fun getAllCharacters(offset: String): CharactersResponse {
        var result: List<com.jorgesm.domain.model.Character> = listOf()
        apiServices.getCharacterList(  offset = offset).data?.results?.let {
            result = it.transformToDomain()
        }
        return CharactersResponse(result)
    }

    override suspend fun getCharacterById(itemId: String): CharactersResponse {
        var result: List<com.jorgesm.domain.model.Character> = listOf()
        apiServices.geCharacterById(itemId).data?.results?.let { results ->
            result = results.transformToDomain()
        }
        return CharactersResponse(result)
    }
}

