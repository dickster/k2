package com.wtw.demo.entity

data class Policy(var name: String = "foo",
                  var location : Array<Location>? =
                          arrayOf(
                              Location( Addr("asdf", "CA", LatLng(123, 123), "Toronto", 1243), "type1"),
                              Location( Addr("dffd", "UK", LatLng(565435, 234), "London", 1243), "type2")
                          ),
                  var vehicle: Vehicle? = Vehicle(),
                  var billing : Billing? = Billing(CreditCard("1231","123" ), "billingDate"),
                  var age: Int? = 44)
