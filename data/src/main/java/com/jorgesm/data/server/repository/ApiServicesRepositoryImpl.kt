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

    override suspend fun getCharacterById(itemId: String): CharactersResponse {
        var result: List<Character> = listOf()
        apiServices.geCharacterById(itemId).data?.results?.first()?.transformToDomain()
        /*apiServices.geCharacterById(itemId).data?.results?.let { results ->
           result= results.transformToDomain()
        }*/
        return CharactersResponse(result)
    }



}
/*
test de api
https://gateway.marvel.com/v1/public/characters/1016823?ts=1&apikey=fc9b4aa59e2c004ecb5ad993ef366531&hash=69eec32c65edc1b0b8643e5ef3012087
*/
