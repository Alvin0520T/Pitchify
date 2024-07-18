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
import com.example.pitchify_main.R;
import com.example.pitchify_main.data.PitchifyDBHelper;

public class aipitching_training extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int REQUEST_STORAGE_PERMISSION_CODE = 2;
    private Button btnRecord;
    private TextView tvTranscript;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aipitching_performance); // Ensure this matches your XML file name

        btnRecord = findViewById(R.id.btnRecord);
        tvTranscript = findViewById(R.id.tvTranscript);

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

                    // Check for specific keywords and respond
                    if (transcript.toLowerCase().contains("hello")) {
                        String reply = "Good day to you!";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("would")) {
                        String reply = "Yes, I would like to have one, how much is it?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("ringgit")) {
                        String reply = "Alright, thank you very much. I would like to have one";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("buy")) {
                        String reply = "Yes, I would like to have one, how much is it?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("question")) {
                        String reply = "Yes, I would like to ask how much is the shipping";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("questions")) {
                        String reply = "Yes, I would like to ask how much is the shipping";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("shipping")) {
                        String reply = "Is there any warranty?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("warranty")) {
                        String reply = "Alright, thank you very much. Have a nice day!";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("price")) {
                        String reply = "Our prices are very competitive.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("feature")) {
                        String reply = "Our product has several unique features that set it apart.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("benefit")) {
                        String reply = "This product offers many benefits, including efficiency and cost savings.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("support")) {
                        String reply = "We provide 24/7 support for all our customers.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("demo")) {
                        String reply = "We can arrange a demo to showcase the product.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("discount")) {
                        String reply = "We offer various discounts and promotions.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("guarantee")) {
                        String reply = "We provide a satisfaction guarantee.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("delivery")) {
                        String reply = "We offer fast and reliable delivery options.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("product")) {
                        String reply = "Our product is known for its high quality and reliability.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("availability")) {
                        String reply = "This product is currently in stock and available for purchase.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("return")) {
                        String reply = "We have a flexible return policy. Would you like more details?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("exchange")) {
                        String reply = "We offer exchanges within 30 days of purchase.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("quality")) {
                        String reply = "We maintain high quality standards for all our products.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("materials")) {
                        String reply = "Our products are made from premium materials.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("cost")) {
                        String reply = "The cost is very reasonable for the value it provides.";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("specifications")) {
                        String reply = "Here are the specifications: [details]. Would you like more information?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("usage")) {
                        String reply = "This product is easy to use. Do you need a demonstration?";
                        textToSpeech.speak(reply, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else if (transcript.toLowerCase().contains("testimonials")) {
                        String reply = "Our customers have given us great feedback.";
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
