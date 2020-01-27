package com.wtw.demo.entity

data class Addr(val postCode: String,
                val country : String,
                val pos : LatLng,
                val city : String,
                val number: Int)
