package com.john.recipe_detail_impl.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Contract used to encapsulate the latitude and longitude of receipt
 * @param longitude long of origin receipt
 * @param latitude lang of origin receipt
 */
@Parcelize
data class Coordinates(
    val longitude: Double,
    val latitude: Double
) : Parcelable
