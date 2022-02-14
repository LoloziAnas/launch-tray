package com.lolozianas.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lolozianas.lunchtray.data.DataSource
import java.text.NumberFormat

const val TAG = "OrderViewModel"
class OrderViewModel : ViewModel() {

    // Map of menu items
    val menuItems = DataSource.menuItems

    // Default values for items prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    // Default tax rate
    private val taxRate = 0.08

    // Entree for the order
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    // Side for the order
    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    // Accompaniment for the order
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    // Subtotal cost for the order
    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Total cost of the order
    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Tax for the order
    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }


    /**
     * Set the entree for the order
     * */
    fun setEntree(entree: String) {
        // if _entree.value is not null, set the previous entree price to the current entree price.
        if (_entree.value != null) {
            previousEntreePrice = _entree.value?.price!!
            //_entree.value!!.price = previousEntreePrice
        }
        // if _subtotal.value is not null subtract the previous entree price from the current
        //  subtotal value. This ensures that we only charge for the currently selected entree.
        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousEntreePrice)
        }
        // set the current entree value to the menu item corresponding to the passed in string
        _entree.value = menuItems[entree]
        // update the subtotal to reflect the price of the selected accompaniment.
        _entree.value?.price?.let { updateSubtotal(it) }
        // Update total subtotal
        updateSubtotal(_entree.value?.price!!)
    }

    /**
     * Set the side for the order*/
    fun setSide(side: String) {

    }

    /**
     * Set the accompaniment for the order
     * */
    fun setAccompaniment(accompaniment: String) {

    }

    /**
     * Update subtotal value
     * */
    private fun updateSubtotal(itemPrice: Double) {

    }

    /**
     * Reset all values pretraining to the order
     * */

    fun resetOrder() {

    }
}


