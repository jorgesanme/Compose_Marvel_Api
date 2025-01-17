package com.jorgesm.domain.repositoy

import com.jorgesm.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllCharacters(): Flow<List<Character>>

    suspend fun saveAllCharacters(characters: List<Character>)

    suspend fun findCharacterById(item: Long): Character

    suspend fun updateCharacter(item: Character)

    suspend fun countCharacter(): Int

}