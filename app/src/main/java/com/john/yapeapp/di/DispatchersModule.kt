package com.john.yapeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
object DispatchersModule {

    @Provides
    fun providesDispatchers(): CoroutineContext = Dispatchers.IO
}