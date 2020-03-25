package com.example.laborator2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotesActivity extends AppCompatActivity {

    private Button loadButton;
    private Button saveButton;
    private EditText note;
    private Switch overwrite;
    private final String fileName="Note.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        loadButton=(Button)findViewById(R.id.loadButton);
        saveButton=(Button)findViewById(R.id.saveButton);
        note=(EditText)findViewById(R.id.note);
        overwrite=(Switch)findViewById(R.id.overwrite);


    }
    public void save(View view) {
        String toSave=note.getText().toString();
        FileOutputStream stream=null;
        try {
            if(overwrite.isChecked()) {
                stream = openFileOutput(fileName, MODE_PRIVATE);
            }
            else
            {
                stream=openFileOutput(fileName,MODE_PRIVATE|MODE_APPEND);
            }
            stream.write(toSave.getBytes());
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"File not found",Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(this,"Cannot write file",Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                if(stream!=null)
                {
                    stream.close();
                }
            }
            catch (IOException e)
            {

            }
    }
}

    public void load(View v)
    {
        FileInputStream stream =null;
       try {
           stream = openFileInput(fileName);
           InputStreamReader streamReader=new InputStreamReader(stream);
           BufferedReader reader=new BufferedReader(streamReader);
           StringBuilder sbuilder=new StringBuilder();
           String line;
           while ((line= reader.readLine())!=null)
           {
                sbuilder.append(line).append("\n");
           }
           note.setText(sbuilder.toString());
           Toast.makeText(this,"Loaded",Toast.LENGTH_LONG).show();

       }
       catch (FileNotFoundException e)
       {

       }
       catch (IOException e)
       {

       }
       finally {
           if(stream!=null)
           {
               try {
                   stream.close();
               }
               catch (IOException e)
               {

               }
           }

       }

    }
}
