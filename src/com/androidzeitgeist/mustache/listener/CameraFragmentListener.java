package com.androidzeitgeist.mustache.listener;

import android.graphics.Bitmap;

import com.androidzeitgeist.mustache.fragment.CameraFragment;

/**
 * Listener interface that has to be implemented by activities using
 * {@link CameraFragment} instances.
 *
 * @author Sebastian Kaspari <sebastian@androidzeitgeist.com>
 */
public interface CameraFragmentListener {
    /**
     * A non-recoverable camera error has happened.
     */
    public void onCameraError();

    /**
     * A picture has been taken.
     *
     * @param bitmap
     */
    public void onPictureTaken(Bitmap bitmap);
}
