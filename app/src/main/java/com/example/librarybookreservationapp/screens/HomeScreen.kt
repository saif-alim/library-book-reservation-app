package com.example.librarybookreservationapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.librarybookreservationapp.book.Book
import com.example.librarybookreservationapp.book.BookListItem
import com.example.librarybookreservationapp.firebase.firestore.FirestoreService

@Composable
fun HomeScreen() {
    val firestoreService = FirestoreService()
    var books by remember { mutableStateOf<List<Book>>(emptyList()) }

    LaunchedEffect(Unit) {
        books = firestoreService.getBooks()
    }

    if (books.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(books) { book ->
            BookListItem(book)
        }
    }
}