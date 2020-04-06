package com.example.laborator2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String Tag="MainActivity";
    private static final int FINE_LOCATION_PERMISSION =10 ;
    private static final int CAMERA_PERMISSION=11;
    private static final int STORAGE_PERMISSION=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Tag,"OnCreate");
        setContentView(R.layout.activity_main);
        final ListView lv = findViewById(R.id.ProductList);
        final List<Product> your_array_list = Product.generateProducts();
        final ArrayAdapter<Product> arrayAdapter = new ArrayAdapter<Product>(
                this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                your_array_list );
        lv.setAdapter(arrayAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                Product p=(Product)arg0.getItemAtPosition(position);
                TextView t=(TextView)findViewById(R.id.CurrentProduct);
                t.setText(p.productName+ " : "+String.valueOf(p.productPrice));
            }
        });
        askLocaitonPermissions();
        askCameraPermissions();
        askStoragePermissions();
    }
    protected void onStart()
    {
        super.onStart();
        Log.i(Tag,"OnStart");
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.i(Tag,"OnRestart");
    }

    protected void onResume()
    {
        super.onResume();
        Log.i(Tag,"OnResume");
    }

    protected void onPause()
    {
        super.onPause();
        Log.i(Tag,"OnPause");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i(Tag,"OnStop");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(Tag,"OnDestroy");
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        TextView t=findViewById(R.id.CurrentProduct);
        outState.putString("Current_Product",t.getText().toString());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        TextView t=findViewById(R.id.CurrentProduct);
        t.setText(savedInstanceState.getString("Current_Product"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Profile:
                Toast.makeText(this,"Profile option Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Notes:
                goToNotes();
                return true;
            case R.id.Exit:
                Toast.makeText(this,"Exit option Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SettingAuto:
                goToSettingsAuto();
                return true;
            case R.id.SettingsManual:
                goToSettingManual();
                return true;
            case R.id.Cart:
                Toast.makeText(this,"Cart option Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ViewCart:
                Toast.makeText(this,"ViewCart option Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Menu:
                goToMenu();
                return true;
            case R.id.EmptyCart:
                Toast.makeText(this,"EmptyCart option Selected",Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }


    }
    public void goToMenu()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToNotes()
    {
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

    public void goToSettingsAuto()
    {
        Intent intent = new Intent(this, PreferancesActivity.class);
        startActivity(intent);
    }

    public void goToSettingManual()
    {
        Intent intent = new Intent(this, ManualSettingsActivity.class);
        startActivity(intent);
    }
    private void askLocaitonPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {

            }
            else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        FINE_LOCATION_PERMISSION);
            }


        }




    }
    private void askCameraPermissions()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
            {

            }
            else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION);
            }


        }
    }

    private void askStoragePermissions()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

            }
            else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION);
            }

        }
    }

}
