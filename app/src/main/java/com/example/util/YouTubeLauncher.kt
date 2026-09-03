package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object YouTubeLauncher {

    fun openVideo(context: Context, videoUrl: String, searchFallbackQuery: String) {
        // Try opening direct video url first
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            return
        } catch (e: Exception) {
            // If direct url fails, fallback to search query
        }

        try {
            val searchUrl = "https://www.youtube.com/results?search_query=" + Uri.encode(searchFallbackQuery)
            val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(fallbackIntent)
        } catch (e2: Exception) {
            Toast.makeText(context, "Unable to launch YouTube on device", Toast.LENGTH_SHORT).show()
        }
    }

    fun searchYouTube(context: Context, query: String) {
        try {
            val searchUrl = "https://www.youtube.com/results?search_query=" + Uri.encode(query)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open YouTube search", Toast.LENGTH_SHORT).show()
        }
    }
}
