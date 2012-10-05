package com.androidzeitgeist.mustache.fragment;

import android.app.Activity;
import android.app.Fragment;

import com.androidzeitgeist.mustache.listener.CameraFragmentListener;

/**
 * Fragment for displaying the camera preview.
 *
 * @author Sebastian Kaspari <sebastian@androidzeitgeist.com>
 */
public class CameraFragment extends Fragment {
    public static final String TAG = "Mustache/CameraFragment";

    private CameraFragmentListener listener;

    /**
     * On activity getting attached.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof CameraFragmentListener)) {
            throw new IllegalArgumentException(
                "Activity has to implement CameraFragmentListener interface"
            );
        }

        listener = (CameraFragmentListener) activity;
    }
}
