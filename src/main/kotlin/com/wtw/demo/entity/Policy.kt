package com.wtw.demo.entity

data class Policy(var name: String = "myName",
                  var location : Array<Location>? =
                          arrayOf(
                              Location(type = "bar"),
                              Location(type = "foo")
                          ),
                  var vehicle: Vehicle? = Vehicle(),
                  var billing : Billing? = Billing(CreditCard(), "billingDate"),
                  var age: Int? = 44)
