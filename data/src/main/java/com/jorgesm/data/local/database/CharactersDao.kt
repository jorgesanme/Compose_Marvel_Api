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
    @Query("select * from localCharacter")
    fun getAllCharacters(): Flow<List<LocalCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<LocalCharacterEntity>)

    @Query("select * from localCharacter where id = :id")
    fun findCharacterById(id: Int): Flow<LocalCharacterEntity>

    @Update
    fun updateCharacter(item: LocalCharacterEntity)
}