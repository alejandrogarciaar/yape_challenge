package com.john.navigation

import android.content.Context

interface DetailNavigation {
    fun goToDetail(context: Context, id: Long)
}