package com.androidzeitgeist.mustache.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.androidzeitgeist.mustache.R;
import com.androidzeitgeist.mustache.listener.CameraFragmentListener;

/**
 * Activity displaying the camera and mustache preview.
 *
 * @author Sebastian Kaspari <sebastian@androidzeitgeist.com>
 */
public class CameraActivity extends Activity implements CameraFragmentListener {
    public static final String TAG = "Mustache/CameraActivity";

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
}
