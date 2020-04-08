package com.example.onlineshop;
import android.content.Intent;
import android.hardware.Camera;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    Camera camera;
    Camera.PictureCallback callback;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);

        callback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                // API 29 --- In order to access external/shared storage I used in the app manifest
                // requestLegacyExternalStorage=true and getExternalStoragePublicDirectory in order to get
                // get a top-level shared/external storage directory for placing files of a particular type.
                File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ File.separator + "ShopOnline");
                if (!directory.exists()) {
                    System.out.println(directory.mkdirs());
                }
                File pictureDirectory = directory;

                String photoFile = "Photo" + new SimpleDateFormat("yyy-dd-mm-hh-mmss").format(new Date()) + ".jpg";

                String filename = pictureDirectory + File.separator + photoFile;

                File picture = new File(filename);
                try {
                    picture.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(picture);
                    outputStream.write(data);
                    System.out.println("Photo " + filename +" was saved!");
                    outputStream.close();
                } catch (Exception error) {
                    System.out.println("Photo " + filename +" was not saved!");
                }

            }
        };
        Button btn = (Button) findViewById(R.id.photo_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, callback);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        resetCamera();
    }

    public void resetCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        if (camera != null) {
            camera.stopPreview();
            try {
                camera.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();
        }
    }

}
