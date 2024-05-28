package com.jorgesm.data.local.repositoryImpl

import com.jorgesm.data.local.database.CharactersDao
import com.jorgesm.data.mappers.transformFromDDBB
import com.jorgesm.data.mappers.transformToDDBB
import com.jorgesm.domain.model.Character
import com.jorgesm.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao):
    LocalRepository {
    override suspend fun saveAllCharacters(characters: List<Character>) {
        charactersDao.insertCharacters(characters = characters.transformToDDBB())
    }
    override fun getAllCharacters(): Flow<List<Character>> =
         charactersDao.getAllCharacters().map { it.transformFromDDBB() }
    override fun findCharacterById(item: Int) {
        charactersDao.findCharacterById(item)
    }

    override fun updateCharacter(item: Character) {
        charactersDao.updateCharacter(item.transformToDDBB())
    }
}