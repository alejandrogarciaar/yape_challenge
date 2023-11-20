package com.john.home_impl.di

import com.john.home_impl.data.repository.HomeRepository
import com.john.home_impl.data.repository.impl.HomeRepositoryImpl
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