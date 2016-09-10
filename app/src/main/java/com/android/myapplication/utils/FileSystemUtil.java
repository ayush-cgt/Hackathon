package com.android.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by manirupani
 */
public class FileSystemUtil {

    public static final String TAG = "FILE SYSTEM UTIL ---- ";

    public static File getOutputFilePath(Context context, String dirName, String fileName) {
        File dir = new File(
                context.getExternalFilesDir(null), dirName);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.d(TAG, "failed to create " + dirName + " directory");
                return null;
            }
        }

        File mediaFile = new File(dir, fileName);
        return mediaFile;
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    public static File getThumbnailFilePath(Context context, String path) {
        File thumbStorageDir = new File(context.getExternalCacheDir(),
                "thumbnail");

        if (!thumbStorageDir.exists()) {
            if (!thumbStorageDir.mkdirs()) {
                Log.d(TAG, "failed to create thumbnail directory");
                return null;
            }
        }

        // Create a media file name
        File mediaFile = new File(thumbStorageDir, path);
        return mediaFile;
    }

    public static void saveToDisk(File path, Bitmap bmp) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(
                    fileOutputStream);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            Log.e("Utility --- ", "Error while writing to file", e);
        }
    }
}
