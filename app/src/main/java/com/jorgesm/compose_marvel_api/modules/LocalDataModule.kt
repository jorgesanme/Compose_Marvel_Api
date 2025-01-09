package com.jorgesm.compose_marvel_api.modules

import android.content.Context
import androidx.room.Room
import com.jorgesm.data.local.database.AppDataBase
import com.jorgesm.data.local.database.CharactersDao
import com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(app, com.jorgesm.data.local.database.AppDataBase::class.java, "compose_marvel_DDBB").build()

    @Singleton
    @Provides
    fun provideCharactersDao(dataBase: com.jorgesm.data.local.database.AppDataBase) = dataBase.charactersDao()

    @Singleton
    @Provides
    fun provideLocalRepositoryImpl(charactersDao: com.jorgesm.data.local.database.CharactersDao): com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl =
        com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl(charactersDao)

}