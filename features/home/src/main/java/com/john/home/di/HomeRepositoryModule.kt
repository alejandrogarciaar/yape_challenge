package com.john.home.di

import com.john.home.data.repository.HomeRepository
import com.john.home.data.repository.impl.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface HomeRepositoryModule {

    @Binds
    fun bindsHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository
}