package com.joshxee.backtothecheckout.core.rule

import com.joshxee.backtothecheckout.core.domain.Cart

interface CartPricingRule {
    /**
     * Apply the pricing rule to the cart
     *
     * The pricing rule should return a pair of the total price and the new cart without the items that the rule applies to
     *
     * @param items The cart to apply the pricing rule to
     * @return A pair of the total price and the new cart
     */
    fun apply(items: Cart): Pair<Double, Cart>
}