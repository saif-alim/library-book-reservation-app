package com.example.librarybookreservationapp.cart

import androidx.compose.runtime.mutableStateListOf
import com.example.librarybookreservationapp.book.Book

object CartService {
    val cartList = mutableStateListOf<CartItem>()

    fun addBookToCart(book: Book, startDate: String, endDate: String) {
        if (cartList.none { it.book == book }) {
            cartList.add(CartItem(book, startDate, endDate))
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        cartList.remove(cartItem)
    }

    fun clearCart() {
        cartList.clear()
    }
}