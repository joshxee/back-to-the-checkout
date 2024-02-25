package com.joshxee.backtothecheckout.core.rule

import com.joshxee.backtothecheckout.core.domain.Cart
import com.joshxee.backtothecheckout.core.domain.PricedCart
import jdk.jfr.Percentage

class BuyOneGetOnePricingRule(
  private val sku: String,
  private val discountPercentage: Double
) : CartPricingRule {
  override fun apply(items: Cart): PricedCart {
    val count = items.count { it.sku == sku }

    // assume it only applies to the first two in the cart
    if(count < 2){
      return PricedCart(items, 0.0)
    }

    val baseCost = items.find { it.sku == sku }?.price!!
    val discountedCost = baseCost - (baseCost * discountPercentage)

    val total = baseCost * (count - 1) + discountedCost
    val newItems = items.filter { it.sku != sku }
    return PricedCart(newItems, total)
  }
}