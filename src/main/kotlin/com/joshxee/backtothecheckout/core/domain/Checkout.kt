package com.joshxee.backtothecheckout.core.domain

data class Checkout(
  val cart: Cart,
  val pricingRules: List<(Cart) -> Pair<Double, Cart>>
)
