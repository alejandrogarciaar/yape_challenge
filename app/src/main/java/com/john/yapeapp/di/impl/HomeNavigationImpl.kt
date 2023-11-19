package com.john.yapeapp.di.impl

import android.content.Context
import android.content.Intent
import com.john.home.presentation.HomeActivity
import com.john.navigation.HomeNavigation
import javax.inject.Inject

class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    override fun goToHome(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
}