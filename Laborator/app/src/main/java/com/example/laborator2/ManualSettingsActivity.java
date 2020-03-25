package com.example.laborator2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ManualSettingsActivity extends AppCompatActivity {

    private final String preferances="User Setting";
    private Switch notifications;
    private Switch viewOnly;
    private EditText cardNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_settings);
        notifications=(Switch)findViewById(R.id.notifications);
        viewOnly=(Switch)findViewById(R.id.viewOnly);
        cardNumber=(EditText)findViewById(R.id.card);
        loadPreferences();
    }

    public void savePreferences(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences(preferances,MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor=sharedPreferences.edit();
        preferenceEditor.putBoolean("Notifications",notifications.isChecked());
        preferenceEditor.putBoolean("View Only",viewOnly.isChecked());
        preferenceEditor.putString("Card Number",cardNumber.getText().toString());
        preferenceEditor.apply();
        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();

    }

    private void loadPreferences()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(preferances,MODE_PRIVATE);
        notifications.setChecked(sharedPreferences.getBoolean("Notifications",false));
        viewOnly.setChecked(sharedPreferences.getBoolean("View Only",false));
        cardNumber.setText(sharedPreferences.getString("Card Number",""));

    }
}
