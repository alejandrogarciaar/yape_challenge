package com.john.home_wiring_impl.navigation

import android.content.Context
import android.content.Intent
import com.john.home_api.navigation.HomeNavigation
import com.john.home_impl.presentation.HomeActivity
import javax.inject.Inject

class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    override fun startNavigation(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
}