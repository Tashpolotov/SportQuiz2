package com.example.sportquiz.module

import com.example.data.SportRepositoryMock
import com.example.repository.SportRepository
import com.example.usecase.SportUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRepository():SportRepository{
        return SportRepositoryMock()
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: SportRepository):SportUseCase{
        return SportUseCase(repository)
    }
}