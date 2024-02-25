package com.joshxee.backtothecheckout.core.domain

data class Item(
  val sku: String,
  val price: Double
)

typealias Cart = List<Item>