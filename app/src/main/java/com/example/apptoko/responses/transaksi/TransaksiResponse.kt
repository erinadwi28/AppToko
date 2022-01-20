package com.example.apptoko.responses.transaksi

data class TransaksiResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)