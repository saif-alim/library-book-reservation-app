package com.example.librarybookreservationapp.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage

@Composable
fun LoadImageFromUrl(url: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    )
}