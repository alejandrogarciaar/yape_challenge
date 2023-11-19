package com.john.home.data.repository

import com.john.home.domain.HomeRecipePreview

interface HomeRepository {
    suspend fun getReceipts(): Result<List<HomeRecipePreview>>
}