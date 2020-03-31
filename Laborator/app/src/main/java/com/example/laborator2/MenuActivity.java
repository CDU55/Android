package com.example.laborator2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void sendMessage(View view)
    {
        EditText t=findViewById(R.id.Message);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, t.getText());
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    public void goToMain(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_MAIN);

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }
    public void openLogin(View view)
    {
        DialogFragment newFragnemnt=new Login();
        newFragnemnt.show(getSupportFragmentManager(),"Login");
    }

    public void goToSensor(View view)
    {
        Intent intent = new Intent(this, SensorsActivity.class);
        startActivity(intent);
    }

    public void goToLocation(View view)
    {
        Intent intent = new Intent(this, CoordinatesActivity.class);
        startActivity(intent);
    }
}
