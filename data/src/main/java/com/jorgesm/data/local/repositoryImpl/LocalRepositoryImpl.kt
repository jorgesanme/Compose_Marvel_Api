package com.jorgesm.data.local.repositoryImpl

import com.jorgesm.data.local.database.CharactersDao
import com.jorgesm.data.mappers.transformFromDDBB
import com.jorgesm.data.mappers.transformToDDBB
import com.jorgesm.domain.model.Character
import com.jorgesm.domain.model.response.CharactersResponse
import com.jorgesm.domain.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao) :
    LocalRepository {
    override suspend fun saveAllCharacters(characters: List<Character>) {
        charactersDao.insertCharacters(characters = characters.map { it.transformToDDBB() })
    }
    override fun getAllCharacters(): Flow<CharactersResponse> =
        charactersDao.getAllCharacters().map { it.transformFromDDBB() }
    override suspend fun findCharacterById(item: Long): Character =
        withContext(Dispatchers.IO) {
            charactersDao.findCharacterById(item).transformFromDDBB()
        }


    override suspend fun updateCharacter(item: Character) {
        charactersDao.updateCharacter(item.transformToDDBB())
    }
}