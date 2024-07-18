package com.example.pitchify_main.aipitching_screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.example.pitchify_main.R;
import com.example.pitchify_main.data.PitchifyDBHelper;

public class aipitching_performance extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private Button btnRecord;
    private TextView tvTranscript;
    private SpeechRecognizer speechRecognizer;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aipitching_performance);

        btnRecord = findViewById(R.id.btnRecord);
        tvTranscript = findViewById(R.id.tvTranscript);

        // Request audio recording permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
        }

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording) {
                    stopRecording();
                } else {
                    startRecording();
                }
            }
        });

        // Display existing transcripts when the activity is created
        displayTranscripts();
    }

    private void startRecording() {
        btnRecord.setText("Stop Recording");
        isRecording = true;

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                tvTranscript.setText("Error: " + error);
                stopRecording();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    String transcript = matches.get(0);

                    // Save the transcript in the database
                    PitchifyDBHelper dbHelper = new PitchifyDBHelper(aipitching_performance.this);
                    dbHelper.updateTranscript(transcript);

                    // Save the transcript to a file
                    saveTranscriptToFile(transcript);

                    // Display the updated transcripts
                    displayTranscripts();
                }
                stopRecording();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    tvTranscript.setText(matches.get(0));
                }
            }

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        speechRecognizer.startListening(intent);
    }

    private void stopRecording() {
        btnRecord.setText("Start Recording");
        isRecording = false;
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.destroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted
        } else {
            // Permission denied
            finish();
        }
    }

    private void displayTranscripts() {
        PitchifyDBHelper dbHelper = new PitchifyDBHelper(this);
        ArrayList<String> transcripts = dbHelper.getAllTranscripts();

        StringBuilder sb = new StringBuilder();
        for (String transcript : transcripts) {
            sb.insert(0, transcript + "\n"); // Add each new transcript at the top
        }

        tvTranscript.setText(sb.toString());
    }

    private void saveTranscriptToFile(String transcript) {
        String fileName = "transcripts.txt";
        try (FileOutputStream fos = openFileOutput(fileName, MODE_APPEND)) {
            fos.write((transcript + "\n").getBytes());
            Log.d("File Writing", "Transcript written to file successfully");
        } catch (IOException e) {
            Log.e("File Writing Error", "Error writing to file: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
