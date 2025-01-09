package com.jorgesm.compose_marvel_api.modules

import com.jorgesm.data.local.repositoryImpl.LocalRepositoryImpl
import com.jorgesm.data.server.repositoryImpl.ApiServicesRepositoryImpl
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
    fun provideGetCharacterListUseCase(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): com.jorgesm.usecases.remote.GetCharactersListUseCase =
        com.jorgesm.usecases.remote.GetCharactersListUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetCharacterById(apiServicesRepositoryImpl: ApiServicesRepositoryImpl): com.jorgesm.usecases.remote.GetCharacterByIdUseCase =
        com.jorgesm.usecases.remote.GetCharacterByIdUseCase(apiServicesRepositoryImpl)

    @Singleton
    @Provides
    fun provideGetLocalCharacterListUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.usecases.local.GetLocalCharacterListUseCase =
        com.jorgesm.usecases.local.GetLocalCharacterListUseCase(localRepository)

    @Singleton
    @Provides
    fun provideSaveCharacterInDataBaseUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.usecases.local.SaveCharacterInDataBaseUseCase =
        com.jorgesm.usecases.local.SaveCharacterInDataBaseUseCase(localRepository)

    @Singleton
    @Provides
    fun provideGetLocalCharacterByIdUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.usecases.local.GetLocalCharacterByIdUseCase =
        com.jorgesm.usecases.local.GetLocalCharacterByIdUseCase(localRepository)

    @Singleton
    @Provides
    fun provideUpdateLocalCharacterUseCase(localRepository: LocalRepositoryImpl): com.jorgesm.usecases.local.UpdateLocalCharacterUseCase =
        com.jorgesm.usecases.local.UpdateLocalCharacterUseCase(localRepository)
}