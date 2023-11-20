package com.john.home_impl.data.repository

import com.john.home_impl.domain.entities.HomeRecipePreview

interface HomeRepository {
    suspend fun getReceipts(): Result<List<HomeRecipePreview>>
}