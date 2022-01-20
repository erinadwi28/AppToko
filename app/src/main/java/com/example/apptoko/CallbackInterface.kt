package com.example.apptoko

import com.example.apptoko.responses.cart.Cart

interface CallbackInterface {
    fun passResultCallback(total:String, cart: ArrayList<Cart>)
}