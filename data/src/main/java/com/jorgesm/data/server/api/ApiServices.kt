package com.jorgesm.data.server.api

import com.jorgesm.data.models.CharacterResponseDTO
import com.jorgesm.data.utils.Const
import com.jorgesm.data.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET(Const.CHARACTERS_LIST_PATH)
    suspend fun getCharacterList(
        @Query("ts") timeStamp: String =  Const.TIME_STAMP.toString(),
        @Query("apikey") apikey: String = Const.API_PUBLIC_KEY,
        @Query("hash") hash: String? = Utils.createEndPointHash(),
        @Query("offset") offset: String
    ): CharacterResponseDTO

    @GET(Const.CHARACTER_BY_ID)
     suspend fun geCharacterById(
        @Path("id")characterId: String,
        @Query("ts") timeStamp: String = Const.TIME_STAMP.toString(),
        @Query("apikey") apikey: String = Const.API_PUBLIC_KEY,
        @Query("hash") hash: String? = Utils.createEndPointHash(),
    ): CharacterResponseDTO
}