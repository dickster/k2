package com.wtw.demo.entity

import java.util.*

data class CreditCard(val number: String =  (""+Math.random()).substring(1,8),
                    val code: String = (""+Math.random()).substring(0,2))

