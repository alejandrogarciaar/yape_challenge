package com.john.home.usecases

import com.john.home.data.repository.HomeRepository
import com.john.home.domain.HomeRecipePreview
import javax.inject.Inject

class GetHomeRecipesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<HomeRecipePreview>> {
        return homeRepository.getReceipts()
    }
}