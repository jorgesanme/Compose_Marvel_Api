package com.jorgesm.data.server.api

import com.jorgesm.data.server.models.CharacterResponseDTO
import com.jorgesm.data.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET(Const.CHARACTERS_LIST_PATH)
    suspend fun getCharacterList(
        @Query("ts") timeStamp: String =  Const.TIME_STAMP.toString(),
        @Query("apikey") apikey: String = Const.API_PUBLIC_KEY,
        @Query("hash") hash: String = Const.API_HAST ,
        @Query("offset") offset: String
    ): CharacterResponseDTO

    @GET(Const.CHARACTER_BY_ID)
     suspend fun geCharacterById(
        @Path("id")characterId: String,
        @Query("ts") timeStamp: String = Const.TIME_STAMP.toString(),
        @Query("apikey") apikey: String = Const.API_PUBLIC_KEY,
        @Query("hash") hash: String = Const.API_HAST,
    ): CharacterResponseDTO
}