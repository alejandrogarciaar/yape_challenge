package com.john.home_impl.data.mappers

import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.dto.HomeRecipePreviewDTO

object HomeRecipeReviewMapper {
    fun map(receipts: List<HomeRecipePreviewDTO>): List<HomeRecipePreview> {
        return receipts.map { item ->
            HomeRecipePreview(
                id = requireNotNull(item.id) { "ReceiptPreviewDTO.id is required" },
                name = requireNotNull(item.name) { "ReceiptPreviewDTO.name is required" },
                image = requireNotNull(item.image) { "ReceiptPreviewDTO.image is required" },
                ingredients = requireNotNull(item.ingredients) { "ReceiptPreviewDTO.ingredients are required" }
            )
        }
    }
}