package com.joshxee.backtothecheckout.core.domain

import com.joshxee.backtothecheckout.core.rule.CartPricingRule

data class Checkout(
  val cart: Cart,
  val pricingRules: List<CartPricingRule>
)