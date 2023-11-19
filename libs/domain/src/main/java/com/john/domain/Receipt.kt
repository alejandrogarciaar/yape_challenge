package com.john.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Contract used to encapsulate the receipt
 * @param id indicates the unique id
 * @param name indicates the description of this receipt
 * @param ingredients indicates the required ingredients to perform the receipt
 * @param image indicates the image url / thumbnail
 * @param coordinates indicates que receipt origin location
 */
@Parcelize
data class Receipt(
    val id: Long,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: String,
    val coordinates: Coordinates
) : Parcelable
