package com.joshxee.backtothecheckout.core.handler

import com.joshxee.backtothecheckout.core.domain.Checkout
import com.joshxee.backtothecheckout.core.domain.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetCheckoutPriceHandlerImplTest {

  private val tested = GetCheckoutPriceHandlerImpl()

  @Test
  fun `should return base price when handle and no pricing rules`() {
    val cart = listOf(
      Item("A", 50.0),
      Item("B", 30.0)
    )
    val checkout = Checkout(
      cart,
      listOf()
    )

    val result = tested.handle(checkout)

    assertEquals(80.0, result)
  }
}