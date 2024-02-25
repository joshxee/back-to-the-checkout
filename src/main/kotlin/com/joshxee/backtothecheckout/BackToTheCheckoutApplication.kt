package com.joshxee.backtothecheckout

import com.joshxee.backtothecheckout.core.domain.Checkout
import com.joshxee.backtothecheckout.core.domain.Item
import com.joshxee.backtothecheckout.core.handler.GetCheckoutPriceHandler
import com.joshxee.backtothecheckout.core.handler.GetCheckoutPriceHandlerImpl
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
    println("Cart: $apple, $banana, $carrot x 3")

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
  }
}

fun main(args: Array<String>) {
  runApplication<BackToTheCheckoutApplication>(*args)
}
