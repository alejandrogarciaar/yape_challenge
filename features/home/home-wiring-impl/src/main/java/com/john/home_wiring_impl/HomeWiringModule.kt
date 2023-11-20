package com.john.home_wiring_impl

import com.john.home_api.navigation.HomeNavigation
import com.john.home_wiring_impl.navigation.HomeNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeWiringModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface HomeNavigationModule {
        @Binds
        fun bindsHomeNavigation(impl: HomeNavigationImpl): HomeNavigation
    }
}