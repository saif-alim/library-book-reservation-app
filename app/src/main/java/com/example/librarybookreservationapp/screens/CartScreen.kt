package com.example.librarybookreservationapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.librarybookreservationapp.cart.CartListItem
import com.example.librarybookreservationapp.cart.CartService

@Composable
fun CartScreen() {
    val context = LocalContext.current
    var checkoutEnabled by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (CartService.cartList.isEmpty()) {
            checkoutEnabled = false

            Text(
                text = "No books currently in cart",
                fontFamily = FontFamily.Serif,
                modifier = Modifier.align(Alignment.Center)
            )

        } else {
            checkoutEnabled = true

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 56.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(CartService.cartList) { cartItem ->
                    CartListItem(
                        cartItem = cartItem,
                        onClickRemove = {
                            CartService.removeFromCart(cartItem)
                        }
                    )
                }

            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                // todo: handle checkout
                Toast.makeText(context, "You have checked out!", Toast.LENGTH_SHORT).show()
                CartService.clearCart()
            },
            enabled = checkoutEnabled
        ) { Text(text = "Checkout") }
    }
}