package com.joshxee.backtothecheckout

import com.joshxee.backtothecheckout.core.domain.Checkout
import com.joshxee.backtothecheckout.core.domain.Item
import com.joshxee.backtothecheckout.core.handler.GetCheckoutPriceHandler
import com.joshxee.backtothecheckout.core.handler.GetCheckoutPriceHandlerImpl
import com.joshxee.backtothecheckout.core.rule.BuyOneGetOnePricingRule
import com.joshxee.backtothecheckout.core.rule.MultiCartPricingRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BackToTheCheckoutApplication {

  @Autowired
  lateinit var getCheckoutPriceHandler: GetCheckoutPriceHandler

  @Bean
  fun runner() = ApplicationRunner {
    runSimulation()
  }

  fun runSimulation() {
    println("Running simulation...")

    val apple = Item("A", 0.60)
    val banana = Item("B", 0.30)
    val carrot = Item("C", 0.25)
    val cart = listOf(apple, banana, carrot, apple, apple)
    println("Cart: $apple x3, $banana, $carrot")

    val multiBuyRule = MultiCartPricingRule("A", 0.60, 1.0, 3)
    println("Rules: MultiCartPricingRule(\"A\", 0.60, 1.0, 3)")

    val checkout = Checkout(
      cart,
      listOf(
        multiBuyRule
      )
    )

    val result = getCheckoutPriceHandler.handle(checkout)

    println("Result: $result")
    println("Simulation complete.")

    println("Running simulation...")
    val cart2 = listOf(apple, apple)
    println("Cart: $apple x2")

    val buyOneGetOnePricingRule = BuyOneGetOnePricingRule("A", 1.0)
    println("Rules: BuyOneGetOnePricingRule(\"A\", 1.0)")

    val checkout2 = Checkout(
      cart2,
      listOf(
        buyOneGetOnePricingRule
      )
    )

    val result2 = getCheckoutPriceHandler.handle(checkout2)

    println("Result: $result2")
    println("Simulation complete.")
  }
}

fun main(args: Array<String>) {
  runApplication<BackToTheCheckoutApplication>(*args)
}
