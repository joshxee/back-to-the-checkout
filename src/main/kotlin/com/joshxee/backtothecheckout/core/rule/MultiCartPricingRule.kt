package com.joshxee.backtothecheckout.core.rule

import com.joshxee.backtothecheckout.core.domain.Cart
import com.joshxee.backtothecheckout.core.domain.PricedCart

class MultiCartPricingRule(
  private val sku: String,
  private val basePrice: Double,
  private val discountPrice: Double,
  private val discountQuantity: Int
) : CartPricingRule {
  override fun apply(items: Cart): PricedCart {
    val itemCount = items.count { it.sku == sku }
    val discountCount = Math.floorDiv(itemCount, discountQuantity)

    // If we do not have enough items to apply the discount, return the original cart
    if (discountCount == 0) {
      return PricedCart(items, 0.0)
    }

    val discountTotal = discountCount * discountPrice

    // Assumption: We will not apply further rules to items of the same sku
    val baseTotal = (itemCount % discountQuantity) * basePrice
    val total = discountTotal + baseTotal
    val newItems = items.filter { it.sku != sku }
    return PricedCart(newItems, total)
  }
}