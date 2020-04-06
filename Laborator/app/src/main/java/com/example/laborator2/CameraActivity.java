package com.example.laborator2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceView photoFrame;
    private SurfaceHolder holder;
    private int activityOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"Permission to access camera or storage denied !", Toast.LENGTH_LONG).show();
            return;
        }

        photoFrame=(SurfaceView)findViewById(R.id.imageFrame);

        camera=Camera.open();

        holder=photoFrame.getHolder();
        holder.addCallback(this);

        activityOrientation=getResources().getConfiguration().orientation;



    }

    public void surfaceCreated(SurfaceHolder holder) {


        Camera.Parameters params=camera.getParameters();
        List<Camera.Size> cameraSizes=params.getSupportedPictureSizes();
        Camera.Size mSize=null;
        for (Camera.Size size:cameraSizes)
        {
            mSize=size;
        }
        if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE)
        {
            params.set("orientation","portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }
        else
        {
            params.set("orientation","landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }
        params.setPictureSize(mSize.width,mSize.height);
        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        camera.stopPreview();
        camera.release();


    }

    public void takePhoto(View view)
    {
        if(camera!=null)
        {
            camera.takePicture(null,null,pictureCallback);
        }
    }

    Camera.PictureCallback pictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            Bitmap decodedBytes= BitmapFactory.decodeByteArray(data,0,data.length);
            Bitmap finalPhoto;
           if(activityOrientation== Configuration.ORIENTATION_PORTRAIT)
            {
                Matrix m=new Matrix();
                m.postRotate(90);
                finalPhoto=Bitmap.createBitmap(decodedBytes,0,0,decodedBytes.getWidth(),decodedBytes.getHeight(),m,true);
            }
            else
            {
                finalPhoto=Bitmap.createBitmap(decodedBytes,0,0,decodedBytes.getWidth(),decodedBytes.getHeight(),null,true);
            }
            String fileName="photo"+currentDateFormat()+".jpg";
            savePhoto(finalPhoto,fileName);
            String message="Image saved to "+"/DCIM/"+fileName;
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            camera.startPreview();
        }
    };

    private void savePhoto(Bitmap photo, String pathFileName) {

        File outputFile=new File(Environment.getExternalStorageDirectory(),"/DCIM/"+pathFileName);
        try
        {
            FileOutputStream stream=new FileOutputStream(outputFile);
            photo.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTime=dateFormat.format(new Date());
        return currentTime;
    }

}
