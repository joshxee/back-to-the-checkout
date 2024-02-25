package com.joshxee.backtothecheckout.core.rule

import com.joshxee.backtothecheckout.core.domain.Cart

interface CartPricingRule {
    fun apply(items: Cart): Pair<Double, Cart>
}