package com.joshxee.backtothecheckout.core.handler

import com.joshxee.backtothecheckout.core.domain.Checkout
import com.joshxee.backtothecheckout.core.domain.Item
import com.joshxee.backtothecheckout.core.rule.CartPricingRule
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import kotlin.random.Random.Default.nextDouble

class GetCheckoutPriceHandlerImplTest {

  private val tested = GetCheckoutPriceHandlerImpl()

  @Test
  fun `should return base price when handle and no pricing rules`() {
    val cart = listOf(
      Item("A${RandomStringUtils.randomAlphabetic(1)}", 50.0),
      Item("B${RandomStringUtils.randomAlphabetic(1)}", 30.0)
    )
    val checkout = Checkout(
      cart,
      listOf()
    )

    val result = tested.handle(checkout)

    assertEquals(80.0, result)
  }

  @Test
  fun `should return rules price when handle and all items follow pricing rule`() {
    val pricingRule = mock<CartPricingRule>()
    val rulesPrice = 10.0

    val itemA = Item("A${RandomStringUtils.randomAlphabetic(1)}", nextDouble(10.0, 100.0))
    val itemB = Item("B${RandomStringUtils.randomAlphabetic(1)}", nextDouble(10.0, 100.0))
    val cart = listOf(
      itemA,
      itemB
    )
    val checkout = Checkout(
      cart,
      listOf(
        pricingRule
      )
    )

    // Given
    given(pricingRule.apply(cart)).willReturn(Pair(rulesPrice, listOf()))

    // When
    val result = tested.handle(checkout)

    // Then
    assertEquals(rulesPrice, result)
  }

  @Test
  fun `should add rules price and base price when handle`() {
    TODO("Not yet implemented")
  }
}