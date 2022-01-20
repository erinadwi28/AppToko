package com.example.apptoko.responses.cart

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cart(
    var id: Int,
    var harga: Int,
    var qty: Int
):Parcelable
