package com.example.librarybookreservationapp.firebase.firestore

import android.util.Log
import com.example.librarybookreservationapp.book.Book
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

const val TAG = "FirestoreService"

class FirestoreService {
    private val db = Firebase.firestore

    suspend fun getBooks(): List<Book> {
        val books = mutableListOf<Book>()
        try {
            val result = db.collection("books").get().await()
            for (document in result) {
                val book = document.toObject<Book>().copy(id = document.id)
                books.add(book)
                Log.d(TAG, "getBooks: added book \n\tName: ${book.bookName} \n\tid: ${book.id}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getBooks: Error getting data: ${e.message}")
        }
        return books
    }
}