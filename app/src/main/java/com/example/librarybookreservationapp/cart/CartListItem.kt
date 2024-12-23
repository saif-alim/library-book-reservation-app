package com.example.librarybookreservationapp.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.librarybookreservationapp.util.LoadImageFromUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartListItem(cartItem: CartItem, onClickRemove: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val book = cartItem.book

    Column {
        Row {
            LoadImageFromUrl(book.bookImage, Modifier.height(100.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = book.bookName, fontWeight = FontWeight.Bold)
                Text(text = "From: ${cartItem.startDate}")
                Text(text = "To: ${cartItem.endDate}")
                Button(
                    onClick = { showDialog = true },
                    content = {
                        Text(text = "View Details")
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    if (showDialog) {
        BasicAlertDialog(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp),
            onDismissRequest = { showDialog = false },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = book.bookName, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Reserved from ${cartItem.startDate} to ${cartItem.endDate}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onClickRemove()
                            showDialog = false
                        },
                        content = {
                            Text(text = "Remove from Cart")
                        }
                    )
                }
            },
        )
    }
}