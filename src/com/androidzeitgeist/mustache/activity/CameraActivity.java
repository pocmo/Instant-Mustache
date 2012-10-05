package com.androidzeitgeist.mustache.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidzeitgeist.mustache.R;
import com.androidzeitgeist.mustache.fragment.CameraFragment;
import com.androidzeitgeist.mustache.listener.CameraFragmentListener;

/**
 * Activity displaying the camera and mustache preview.
 *
 * @author Sebastian Kaspari <sebastian@androidzeitgeist.com>
 */
public class CameraActivity extends Activity implements CameraFragmentListener {
    public static final String TAG = "Mustache/CameraActivity";

    private static final int PICTURE_QUALITY = 90;

    /**
     * On activity getting created.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
    }

    /**
     * On fragment notifying about a non-recoverable problem with the camera.
     */
    @Override
    public void onCameraError() {
        Toast.makeText(
            this,
            getString(R.string.toast_error_camera_preview),
            Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    /**
     * The user wants to take a picture.
     *
     * @param view
     */
    public void takePicture(View view) {
        view.setEnabled(false);

        CameraFragment fragment = (CameraFragment) getFragmentManager().findFragmentById(
            R.id.camera_fragment
        );

        fragment.takePicture();
    }

    /**
     * A picture has been taken.
     */
    public void onPictureTaken(Bitmap bitmap) {
        File mediaStorageDir = new File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ),
            getString(R.string.app_name)
        );

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                showSavingPictureErrorToast();
                return;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(
            mediaStorageDir.getPath() + File.separator + "MUSTACHE_"+ timeStamp + ".jpg"
        );

        try {
            FileOutputStream stream = new FileOutputStream(mediaFile);
            bitmap.compress(CompressFormat.JPEG, PICTURE_QUALITY, stream);
        } catch (IOException exception) {
            showSavingPictureErrorToast();

            Log.w(TAG, "IOException during saving bitmap", exception);
            return;
        }

        MediaScannerConnection.scanFile(
            this,
            new String[] { mediaFile.toString() },
            new String[] { "image/jpeg" },
            null
        );

        Toast.makeText(
            this,
            getString(R.string.toast_saved_picture, mediaFile.toString()),
            Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    private void showSavingPictureErrorToast() {
        Toast.makeText(this, getText(R.string.toast_error_save_picture), Toast.LENGTH_SHORT).show();
    }
}
