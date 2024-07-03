package com.example.pitchify_main.chatbot.data.data

import android.graphics.Bitmap


data class Chat (
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)