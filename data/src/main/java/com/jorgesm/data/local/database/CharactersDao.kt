package com.jorgesm.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jorgesm.data.local.entity.LocalCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * FROM LocalCharacter")
    fun getAllCharacters(): Flow<List<LocalCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<LocalCharacterEntity>)

    @Query("SELECT * FROM LocalCharacter WHERE id = :id")
    suspend fun findCharacterById(id: Long): LocalCharacterEntity

    @Update
    suspend fun updateCharacter(item: LocalCharacterEntity)
    @Query("SELECT COUNT(id) FROM LOCALCHARACTER")
    suspend fun charactersCount(): Int
}