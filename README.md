# Back to the Checkout

This project is a solution to the Kata 09 problem. It involves creating a checkout system with the ability to add and remove items, apply pricing rules, and calculate the total price.

## Project Structure

The project is structured under `/src/main/kotlin/com.joshxee.backtothecheckout` as follows:
    
```
core
├── domain
│   ├── Checkout.kt
│   └── Item.kt
├── handler
│   └── GetCheckoutPriceHandler.kt
└── rule
    ├── CartPricingRule.kt
    └── ExamplePricingRule.kt
```
 - `domain` contains the domain classes for the checkout system.
 - `handler` contains the handler for the checkout system. In a production environment, this would be called by the adaptor layer, e.g. a controller in a web application.
 - `rule` contains the interface and concrete pricing rules for the checkout system.

## Setup and Running

### Pre-requisites
 - Kotlin
 - Gradle
 - Java 17

> It is recommended to use intelliJ IDEA to run the project.

The file `BackToTheChackoutApplication.kt` runs a simple example of the checkout system. To run the example, simply run the main function in this file.
You can also run the command:

```
./gradlew bootrun
```

## Running the tests

> It is recommended to use intelliJ IDEA to run the tests.
 
The tests can also be run using the following command:

```
./gradlew test
```

## License

This project is licensed under the MIT License.