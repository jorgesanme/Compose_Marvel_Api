package com.jorgesm.domain.repository

import com.jorgesm.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllCharacters(): Flow<List<Character>>
    suspend fun saveAllCharacters(characters: List<Character>)

    fun findCharacterById(item: Int)

    fun updateCharacter(item: Character)

}