package com.joshxee.backtothecheckout.core.handler

import com.joshxee.backtothecheckout.core.domain.Checkout

interface GetCheckoutPriceHandler {
    fun handle(checkout: Checkout): Double
}