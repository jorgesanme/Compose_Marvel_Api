package com.jorgesm.compose_marvel_api.modules

import com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl
import com.jorgesm.data.server.repositoryImpl.ApiServicesRepositoryImpl
import com.jorgesm.casodeuso.local.GetLocalCharacterByIdUseCase
import com.jorgesm.casodeuso.local.GetLocalCharacterListUseCase
import com.jorgesm.casodeuso.local.SaveCharacterInDataBaseUseCase
import com.jorgesm.casodeuso.local.UpdateLocalCharacterUseCase
import com.jorgesm.casodeuso.remote.GetCharacterByIdUseCase
import com.jorgesm.casodeuso.remote.GetCharactersListUseCase
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
    fun provideGetCharacterListUseCase(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): com.jorgesm.casodeuso.remote.GetCharactersListUseCase =
        com.jorgesm.casodeuso.remote.GetCharactersListUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetCharacterById(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): com.jorgesm.casodeuso.remote.GetCharacterByIdUseCase =
        com.jorgesm.casodeuso.remote.GetCharacterByIdUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetLocalCharacterListUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.casodeuso.local.GetLocalCharacterListUseCase =
        com.jorgesm.casodeuso.local.GetLocalCharacterListUseCase(localRepository)

    @Singleton
    @Provides
    fun provideSaveCharacterInDataBaseUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.casodeuso.local.SaveCharacterInDataBaseUseCase =
        com.jorgesm.casodeuso.local.SaveCharacterInDataBaseUseCase(localRepository)

    @Singleton
    @Provides
    fun provideGetLocalCharacterByIdUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.casodeuso.local.GetLocalCharacterByIdUseCase =
        com.jorgesm.casodeuso.local.GetLocalCharacterByIdUseCase(localRepository)

    @Singleton
    @Provides
    fun provideUpdateLocalCharacterUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.casodeuso.local.UpdateLocalCharacterUseCase =
        com.jorgesm.casodeuso.local.UpdateLocalCharacterUseCase(localRepository)
}