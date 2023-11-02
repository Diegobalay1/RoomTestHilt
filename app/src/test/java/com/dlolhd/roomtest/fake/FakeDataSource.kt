package com.dlolhd.roomtest.fake

import com.dlolhd.roomtest.data.Product

object FakeDataSource {
    const val proNameOne = "S21"
    const val proNameTwo = "S22"
    const val quantityOne = 450
    const val quantityTwo = 560
    val productList = listOf(
        Product(
            id = 1,
            productName = proNameOne,
            quantity = quantityOne
        ),
        Product(
            id = 2,
            productName = proNameTwo,
            quantity = quantityTwo
        ),
    )
}