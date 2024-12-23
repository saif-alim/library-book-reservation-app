package com.example.librarybookreservationapp.cart

import com.example.librarybookreservationapp.book.Book

data class CartItem (
    val book: Book,
    val startDate: String,
    val endDate: String
)
