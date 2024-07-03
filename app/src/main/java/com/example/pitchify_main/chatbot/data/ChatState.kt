package com.example.pitchify_main.chatbot.data

import android.graphics.Bitmap
import com.example.pitchify_main.chatbot.data.data.Chat


data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)