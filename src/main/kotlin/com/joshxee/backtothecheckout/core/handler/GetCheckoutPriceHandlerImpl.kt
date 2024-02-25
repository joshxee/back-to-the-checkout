package com.joshxee.backtothecheckout.core.handler

import com.joshxee.backtothecheckout.core.domain.Checkout
import com.joshxee.backtothecheckout.core.domain.PricedCart
import org.springframework.stereotype.Component

@Component
class GetCheckoutPriceHandlerImpl : GetCheckoutPriceHandler {
  override fun handle(checkout: Checkout): Double {
    val pricingRules = checkout.pricingRules

    // Apply pricing rules to the cart
    val pricedCart = pricingRules.fold(
      PricedCart(checkout.cart, 0.0)
    ) { currentCart, pricingRule ->
      val updatedCart = pricingRule.apply(currentCart.cart)
      PricedCart(updatedCart.cart, currentCart.price + updatedCart.price)
    }

    // Calculate the total price by adding the standard price items
    return pricedCart.price + pricedCart.cart.sumOf { it.price }
  }
}