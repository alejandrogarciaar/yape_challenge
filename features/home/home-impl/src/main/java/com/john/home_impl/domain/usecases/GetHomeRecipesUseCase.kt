package com.john.home_impl.domain.usecases

import com.john.home_impl.data.repository.HomeRepository
import com.john.home_impl.domain.entities.HomeRecipePreview
import javax.inject.Inject

class GetHomeRecipesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<HomeRecipePreview>> {
        return homeRepository.getReceipts()
    }
}