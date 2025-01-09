package com.jorgesm.data.local.repositoryImpl

import com.jorgesm.data.local.database.CharactersDao
import com.jorgesm.data.mappers.transformFromDDBB
import com.jorgesm.data.mappers.transformToDDBB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao) :
    com.jorgesm.domain.repositoy.LocalRepository {
    override suspend fun saveAllCharacters(characters: List<com.jorgesm.domain.model.Character>) {
        charactersDao.insertCharacters(characters = characters.map { it.transformToDDBB() })
    }
    override fun getAllCharacters(): Flow<com.jorgesm.domain.model.response.CharactersResponse> =
        charactersDao.getAllCharacters().map { it.transformFromDDBB() }
    override suspend fun findCharacterById(item: Long): com.jorgesm.domain.model.Character =
        withContext(Dispatchers.IO) {
            charactersDao.findCharacterById(item).transformFromDDBB()
        }


    override suspend fun updateCharacter(item: com.jorgesm.domain.model.Character) {
        charactersDao.updateCharacter(item.transformToDDBB())
    }
}