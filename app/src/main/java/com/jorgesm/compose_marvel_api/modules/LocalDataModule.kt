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
    ) = Room.databaseBuilder(app, AppDataBase::class.java, "compose_marvel_DDBB").build()

    @Singleton
    @Provides
    fun provideCharactersDao(dataBase: AppDataBase) = dataBase.charactersDao()

    @Singleton
    @Provides
    fun provideLocalRepository(charactersDao: CharactersDao): LocalRepositoryImpl =
        LocalRepositoryImpl(charactersDao)

}