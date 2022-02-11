package com.lolozianas.lunchtray.model

import java.text.NumberFormat

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val type: Int
) {
    /** Getter method for price
     * Includes formatting
     * */
    fun getFormattedPrice() = NumberFormat.getCurrencyInstance().format(price)
}