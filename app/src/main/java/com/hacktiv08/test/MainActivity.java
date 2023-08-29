package com.hacktiv08.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText komentarEditText;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_KEY = "com.hacktiv08.test.COMMENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        komentarEditText = findViewById(R.id.komentar);
        Button submitButton = findViewById(R.id.button);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        submitButton.setOnClickListener(v -> {
            String komentar = komentarEditText.getText().toString();
            saveComment(komentar);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String savedComment = sharedPreferences.getString("comment", "");
        komentarEditText.setText(savedComment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String komentar = komentarEditText.getText().toString();
        saveComment(komentar);
    }

    private void saveComment(String comment) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("comment", comment);
        editor.apply();
    }
}
