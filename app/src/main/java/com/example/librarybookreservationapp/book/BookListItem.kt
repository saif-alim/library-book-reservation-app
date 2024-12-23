package com.example.librarybookreservationapp.book

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.librarybookreservationapp.cart.CartService
import com.example.librarybookreservationapp.cart.CartService.cartList
import com.example.librarybookreservationapp.util.LoadImageFromUrl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListItem(book: Book) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var errorHeader by remember { mutableStateOf("Sorry there was an issue") }
    var errorMessage by remember { mutableStateOf("Please try again later") }
    val dateRangePickerState = rememberDateRangePickerState()
    val selectedStartDate = dateRangePickerState.selectedStartDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    val selectedEndDate = dateRangePickerState.selectedEndDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Column {
        Row {
            LoadImageFromUrl(book.bookImage, Modifier.height(100.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = book.bookName, fontWeight = FontWeight.Bold)
                Text(text = book.bookAuthors)
                Text(text = book.bookPublisher)
                Button(
                    onClick = {
                        if (cartList.none { it.book == book }) {
                            showDatePicker = true
                        } else {
                            errorHeader = "Book already in cart"
                            errorMessage = "This book is already in your cart. Please remove it before adding it again."
                            showAlertDialog = true
                        }
                    },
                    content = {
                        Text(text = "Reserve Book")
                    }
                )
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if (selectedStartDate.isEmpty() || selectedEndDate.isEmpty()) {
                                        errorHeader = "Invalid date range"
                                        errorMessage = "Please select a valid date range."
                                        showAlertDialog = true
                                        return@Button
                                    }
                                    showDatePicker = false
                                    CartService.addBookToCart(book, selectedStartDate, selectedEndDate)
                                },
                            ) { Text("Confirm") }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDatePicker = false },
                            ) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DateRangePicker(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .padding(16.dp),
                            state = dateRangePickerState,
                            headline = { Text("Select a date") }
                        )
                    }
                }
                if (showAlertDialog) {
                    AlertDialog(
                        onDismissRequest = { showAlertDialog = false },
                        title = { Text(errorHeader) },
                        text = { Text(errorMessage) },
                        confirmButton = {
                            Button(
                                onClick = { showAlertDialog = false },
                            ) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}