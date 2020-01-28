package com.wtw.demo.entity

data class Addr(val postCode: String = (""+Math.random()).substring(1,6),
                val country : String = "CA",
                val pos : LatLng = LatLng(),
                val city : String = "Toronto",
                val number: Int = 33)
