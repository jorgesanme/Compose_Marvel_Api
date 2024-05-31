package com.jorgesm.compose_marvel_api.modules

import com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl
import com.jorgesm.data.server.repositoryImpl.ApiServicesRepositoryImpl
import com.jorgesm.usecases.local.GetLocalCharacterById
import com.jorgesm.usecases.local.GetLocalCharacterListUseCase
import com.jorgesm.usecases.local.SaveCharacterInDataBaseUseCase
import com.jorgesm.usecases.remote.GetCharacterByIdUseCase
import com.jorgesm.usecases.remote.GetCharactersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCharacterListUseCase(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): GetCharactersListUseCase =
        GetCharactersListUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetCharacterById(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): GetCharacterByIdUseCase =
        GetCharacterByIdUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetLocalCharacterListUseCase(localRepository: LocalRepositoryImpl): GetLocalCharacterListUseCase =
        GetLocalCharacterListUseCase(localRepository)

    @Singleton
    @Provides
    fun provideSaveCharacterInDataBaseUseCase(localRepository: LocalRepositoryImpl): SaveCharacterInDataBaseUseCase =
        SaveCharacterInDataBaseUseCase(localRepository)

    @Singleton
    @Provides
    fun provideGetLocalCharacterByIdUseCase(localRepository: LocalRepositoryImpl): GetLocalCharacterById =
        GetLocalCharacterById(localRepository)
}