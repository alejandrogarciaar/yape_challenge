package com.john.yapeapp.di

import com.john.navigation.DetailNavigation
import com.john.navigation.HomeNavigation
import com.john.yapeapp.di.impl.DetailNavigationImpl
import com.john.yapeapp.di.impl.HomeNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface NavigationModule {

    @Binds
    fun bindsHomeNavigation(homeNavigation: HomeNavigationImpl): HomeNavigation

    @Binds
    fun bindsDetailNavigation(detailNavigation: DetailNavigationImpl): DetailNavigation
}