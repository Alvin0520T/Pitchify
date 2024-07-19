package com.example.pitchify_main

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class Pitching : ComponentActivity() {

    private lateinit var buttonUpload: Button
    private lateinit var textViewFeedback: TextView

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedback)

        buttonUpload = findViewById(R.id.button_upload_text)
        textViewFeedback = findViewById(R.id.textView_feedback)

        val filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                readTextFile(it)
            }
        }

        buttonUpload.setOnClickListener {
            filePickerLauncher.launch("text/plain")
        }
    }

    private fun readTextFile(uri: Uri) {
        contentResolver.openInputStream(uri)?.bufferedReader().use { reader ->
            val fileContent = reader?.readText() ?: ""
            sendTextToOpenAI(fileContent)
        }
    }

    private fun sendTextToOpenAI(text: String) {
        val json = JSONObject().apply {
            put("model", "gpt-3.5-turbo-instruct")
            put("prompt", "You are a Senior in a company that is training a intern about his skills in pitching sales on the company product and you guys are currently having a simulation pitching based on his pitching skills can you provide some feedback based on 1. what went well and 2. even better if. Provide feedback on the following text with the structure of 4 key points format of what went well and 4 key points of even better if:\n\n$text")
            put("max_tokens", 200)
            put("temperature", 0.7)
        }

        val body = json.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .post(body)
            .addHeader("Authorization", "Bearer sk-proj-n0nc4b0hPeKCGcmicjVLT3BlbkFJD5Jy9xvKyAjnusexKZv6")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@Pitching, "Failed to get feedback", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseBody ->
                    val jsonResponse = JSONObject(responseBody)
                    val feedbackText = jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getString("text")

                    runOnUiThread {
                        textViewFeedback.text = feedbackText
                    }
                }
            }
        })
    }
}







