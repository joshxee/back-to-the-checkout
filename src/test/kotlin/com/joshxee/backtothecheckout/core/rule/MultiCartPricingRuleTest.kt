package com.joshxee.backtothecheckout.core.rule

import com.joshxee.backtothecheckout.core.domain.Cart
import com.joshxee.backtothecheckout.core.domain.Item
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiCartPricingRuleTest {

  @Test
  fun `should not apply when no skus match`() {
    // Given
    val tested = MultiCartPricingRule(
      sku = "A",
      discountPrice = 130.0,
      basePrice = 50.0,
      discountQuantity = 3
    )

    val cart = listOf(
      Item(sku = "B", price = 30.0),
      Item(sku = "C", price = 20.0)
    )

    // When
    val result = tested.apply(cart)

    // Then
    assertEquals(0.0, result.first)
    assertEquals(cart, result.second)
  }

  @ParameterizedTest
  @MethodSource("applyToSingleSku")
  fun `should apply to all skus that match`(sku: String, discountPrice: Double) {
    // Given
    val tested = MultiCartPricingRule(
      sku = sku,
      discountPrice = discountPrice,
      basePrice = 50.0,
      discountQuantity = 3
    )

    val cart = listOf(
      Item(sku = sku, price = 50.0),
      Item(sku = sku, price = 50.0),
      Item(sku = sku, price = 50.0)
    )

    // When
    val result = tested.apply(cart)

    // Then
    assertEquals(discountPrice, result.first)
    assertEquals(listOf<Item>(), result.second)
  }

  private fun applyToSingleSku(): Stream<Arguments> {
    return Stream.of(
      Arguments.of("A", 130.0),
      Arguments.of("B", 0.0),
      Arguments.of("C", 36.31)
    )
  }

  @Test
  fun `should combine multi price with base price`() {
    // Given
    val tested = MultiCartPricingRule(
      sku = "A",
      discountPrice = 130.0,
      basePrice = 50.0,
      discountQuantity = 3
    )

    val cart = listOf(
      Item(sku = "A", price = 50.0),
      Item(sku = "A", price = 50.0),
      Item(sku = "A", price = 50.0),
      Item(sku = "A", price = 50.0)
    )

    // When
    val result = tested.apply(cart)

    // Then
    assertEquals(180.0, result.first)
    assertEquals(listOf<Item>(), result.second)
  }

  @Test
  fun `should return skus not involved in multi price`() {
    // Given
    val tested = MultiCartPricingRule(
      sku = "A",
      discountPrice = 130.0,
      basePrice = 50.0,
      discountQuantity = 3
    )

    val cart = listOf(
      Item(sku = "A", price = 50.0),
      Item(sku = "A", price = 50.0),
      Item(sku = "A", price = 50.0),
      Item(sku = "B", price = 50.0)
    )

    // When
    val result = tested.apply(cart)

    // Then
    assertEquals(130.0, result.first)
    assertEquals(listOf(Item(sku = "B", price = 50.0)), result.second)
  }
}