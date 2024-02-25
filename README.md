# Back to the Checkout

This project is a solution to the Kata 09 problem. It involves creating a checkout system with the ability to add and remove items, apply pricing rules, and calculate the total price.

## Project Structure

The project is structured under `/src/main/kotlin/com.joshxee.backtothecheckout` as follows:
    
```
core
├── domain
│   ├── Checkout.kt
│   └── Product.kt
├── handler
│   └── GetCheckoutPriceHandler.kt
└── rule
    ├── StandardPricingRule.kt
    └── MultiPricingRule.kt
```
handler: Entry point for this exercise. This directory contains the GetCheckoutPriceHandler.kt file. This file contains the main function that is used to calculate the total price of the Checkout.
rules: This directory contains the StandardPricingRule.kt and MultiPricingRule.kt files. These files define the pricing rules that can be applied to the Checkout.
domain: This directory contains the Checkout.kt and Product.kt files. These files define the Checkout and Product classes.

## Setup and Running

To set up and run the project, follow these steps:

1. Clone the repository.
2. Open the project in IntelliJ IDEA 2023.3.3.
3. Run the project using the built-in Gradle wrapper.

## Contributing

Contributions are welcome. Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License.