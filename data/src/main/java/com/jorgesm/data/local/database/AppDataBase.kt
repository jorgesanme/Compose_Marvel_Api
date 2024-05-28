package com.jorgesm.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jorgesm.data.local.entity.LocalCharacterEntity

@Database(entities = [LocalCharacterEntity::class], version = 1, exportSchema = false)

abstract class AppDataBase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}