package com.joshxee.backtothecheckout.core.handler

import com.joshxee.backtothecheckout.core.domain.Checkout
import org.springframework.stereotype.Component

@Component
class GetCheckoutPriceHandlerImpl : GetCheckoutPriceHandler {
  override fun handle(checkout: Checkout): Double {
    val pricingRules = checkout.pricingRules

    // Apply pricing rules to the cart
    val (subtotal, standardPriceItems) = pricingRules.fold(
      Pair(0.0, checkout.cart)
    ) { (currentSubtotal, currentCart), rule ->
      val (newRuleCosts, remainingCart) = rule(currentCart)
      Pair(currentSubtotal + newRuleCosts, remainingCart)
    }

    // Calculate the total price by adding the standard price items
    return subtotal + standardPriceItems.sumOf { it.price }
  }
}