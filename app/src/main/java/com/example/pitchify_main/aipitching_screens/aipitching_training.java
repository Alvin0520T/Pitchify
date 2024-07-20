package com.example.pitchify_main.aipitching_screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

import com.example.pitchify_main.Pitching;
import com.example.pitchify_main.R;
import com.example.pitchify_main.dashboard_screens.dashboard_overview;
import com.example.pitchify_main.dashboard_screens.staff_profile;
import com.example.pitchify_main.data.PitchifyDBHelper;
import com.example.pitchify_main.resource_screens.resources_training;

public class aipitching_training extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int REQUEST_STORAGE_PERMISSION_CODE = 2;
    private Button btnRecord, feedbackButton;

    private TextView tvTranscript;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aipitching_performance); // Ensure this matches your XML file name

        btnRecord = findViewById(R.id.btnRecord);
        feedbackButton = findViewById(R.id.feedbackbutton1);
        tvTranscript = findViewById(R.id.tvTranscript);
        ImageView navigation1 = findViewById(R.id.nav_1);
        ImageView navigation2 = findViewById(R.id.nav_2);
        ImageView navigation3 = findViewById(R.id.nav_3);
        ImageView navigation4 = findViewById(R.id.nav_4);

        // Request audio recording permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
        }

        // Request storage permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION_CODE);
        }

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language specified is not supported!");
                    }
                } else {
                    Log.e("TTS", "Initialization Failed!");
                }
            }
        });

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

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_training.this, Pitching.class);
                startActivity(intent);
            }
        });

        navigation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_training.this, resources_training.class);
                startActivity(intent);
            }
        });

        navigation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_training.this, dashboard_overview.class);
                startActivity(intent);
            }
        });

        navigation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_training.this, aipitching_task.class);
                startActivity(intent);
            }
        });

        navigation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aipitching_training.this, staff_profile.class);
                startActivity(intent);
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

                    // Check for specific keywords and respond with Chris's conversation
                    if (transcript.toLowerCase().contains("afternoon")) {
                        String reply = "Hi! I'm interested in buying a drone. I’ve heard a lot about them and I’m quite fascinated by the idea. Can you tell me more about them?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("using a drone")) {
                        String reply = "I’m mainly interested in aerial photography and videography. I love capturing landscapes and I think a drone would add a whole new dimension to my shots.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("starting")) {
                        String reply = "No, I’m a complete beginner.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("beginner")) {
                        String reply = "Sounds perfect! What’s the camera quality like?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("4k")) {
                        String reply = "That sounds impressive. What about the flight time? How long can it stay in the air?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("minutes")) {
                        String reply = "That’s reassuring. How easy is it to control?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("direction")) {
                        String reply = "That sounds like exactly what I need. What’s the price?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("greater")) {
                        String reply = "I think I’ll go with the bundle. The extra batteries and carrying case will definitely come in handy.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("landing")) {
                        String reply = "I’ll take a memory card as well, just to make sure I have enough storage.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("space")) {
                        String reply = "That’s all for now. Thank you so much for your help!";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("confident")) {
                        String reply = "Thank you! I’m excited to get started.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    }


                    // Save the transcript in the database
                    PitchifyDBHelper dbHelper = new PitchifyDBHelper(aipitching_training.this);
                    dbHelper.updateTranscript(transcript);

                    // Save the transcript to a file in internal storage
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

    private void saveTranscriptToFile(String transcript) {
        String fileName = "transcripts.txt";
        File internalFile = new File(getFilesDir(), fileName);

        try (FileOutputStream fos = new FileOutputStream(internalFile, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(transcript + "\n");
            Log.d("File Save", "Transcript saved to " + internalFile.getAbsolutePath());
        } catch (IOException e) {
            Log.e("File Save Error", "Error saving transcript: " + e.getMessage());
        }

        // Save a copy to external storage
        saveTranscriptToExternalStorage(transcript);
    }

    private void saveTranscriptToExternalStorage(String transcript) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            File externalFile = new File(getExternalFilesDir(null), "transcripts.txt");

            try (FileOutputStream fos = new FileOutputStream(externalFile, true);
                 OutputStreamWriter osw = new OutputStreamWriter(fos);
                 BufferedWriter writer = new BufferedWriter(osw)) {
                writer.write(transcript + "\n");
                Log.d("External File Save", "Transcript saved to " + externalFile.getAbsolutePath());
            } catch (IOException e) {
                Log.e("External File Save Error", "Error saving transcript: " + e.getMessage());
            }
        } else {
            Log.e("External File Save Error", "Permission not granted to write to external storage");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted for audio recording
        } else if (requestCode == REQUEST_STORAGE_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted for storage
        } else {
            // Permission denied
            finish();
        }
    }

    private void displayTranscripts() {
        String fileName = "transcripts.txt";
        File file = new File(getFilesDir(), fileName);
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.insert(0, line + "\n"); // Add each new transcript at the top
            }
        } catch (IOException e) {
            Log.e("File Read Error", "Error reading transcript: " + e.getMessage());
        }

        tvTranscript.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }


}