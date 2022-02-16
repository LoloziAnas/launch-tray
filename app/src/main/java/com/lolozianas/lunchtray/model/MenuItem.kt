package com.lolozianas.lunchtray.model

import java.text.NumberFormat

data class MenuItem(
    val name: String,
    val description: String,
    var price: Double,
    val type: Int
) {
    /** Getter method for price
     * Includes formatting
     * */
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}