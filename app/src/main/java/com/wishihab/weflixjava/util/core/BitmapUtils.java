package com.wishihab.weflixjava.util.core;



import static com.wishihab.weflixjava.util.core.IOUtils.close;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;


import com.wishihab.weflixjava.model.core.ExifImageInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public final class BitmapUtils {

    private static final int MAX_WIDTH  = 1280;
    private static final int MAX_HEIGHT = 1280;
    private static final int QUALITY = 85;


    /** Get resized JPEG bytes from URI.
     *
     * @param context
     * @param uri
     * @return
     *      Byte array of scaled down image, or null if decode failed.
     */
    @Nullable
    public static byte[] resizeAndGetAsJpegBytes(@NonNull Context context, @NonNull Uri uri) {
        Bitmap bitmap = resizeAndGet(context, uri);
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, bos);
        return bos.toByteArray();
    }

    /** Get resized image bytes from content URI as Bitmap.
     *
     * @param context
     * @param uri
     * @return
     *      Get resized decoded bitmap, or null if decode failed.
     */
    @Nullable
    public static Bitmap resizeAndGet(@NonNull Context context, @NonNull Uri uri) {
        return twoPassDecode(context, uri);
    }

    /*
     * Resizing image in 2 pass to prevent out of memory on very large image
     */
    @Nullable
    private static Bitmap twoPassDecode(@NonNull Context context, @NonNull Uri uri) {
        // pass #1: decode the down sampled image (in the factor of 2 from the original image)
        // and get exif rotation info if available
        Bitmap bitmap = decodeSampledBitmap(context, uri, MAX_WIDTH, MAX_HEIGHT);

        // pass #2: scale image to target size, if required
        bitmap = mayScale(bitmap, MAX_WIDTH, MAX_HEIGHT);

        // pass #3 may apply transform according extra information from exif
        // the transformation step use the smallest image from last step
        // to avoid out of memory
        ExifImageInfo info = readOrientationFromExif(context, uri);
        bitmap = mayTransform(bitmap, info);

        return bitmap;
    }

    /** May transform bitmap according to extra info, if applicable.
     *  It mainly used to correct image orientation on _some_ camera.
     *
     * @param bitmap
     * @param info
     * @return
     */
    @Nullable
    private static Bitmap mayTransform(@Nullable Bitmap bitmap, @Nullable ExifImageInfo info) {
        if (bitmap == null || info == null || info.getRotation() == null || info.getRotation() == 0) {
            return bitmap; // unchanged
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(info.getRotation());
        Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return result;
    }

    /** May scale bitmap according to requested size if image larger than requested size.
     *
     * @param bitmap
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    @Nullable
    private static Bitmap mayScale(@Nullable Bitmap bitmap, int maxWidth, int maxHeight) {
        if (bitmap==null) {
            return null;
        }
        if (bitmap.getWidth() < maxWidth && bitmap.getHeight() < maxHeight) {
            return bitmap;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int targetW = maxWidth;
        int targetH = maxHeight;
        if (w > h) {
            targetH = h * targetW / w;
        } else {
            targetW = w * targetH / h;
        }
        return Bitmap.createScaledBitmap(bitmap, targetW, targetH, false);
    }

    /**  Read exif info from content uri. The URI must be able to read multiple time.
     *
     * @param uri
     * @return
     * @throws IOException
     */
    @NonNull
    public static ExifImageInfo readOrientationFromExif(Context context, Uri uri) {
        ExifImageInfo info;
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            info = readOrientationFromExif(is);
        } catch (IOException|SecurityException e) {
            info = new ExifImageInfo();
        } finally {
            close(is);
        }
        return info;
    }

    /** Read orientation exif info from input stream.
     *  The caller responsible for opening and closing the stream.
     *
     * @param is
     * @return
     *      Exif info, only orientation info is read.
     * @throws IOException
     */
    @NonNull
    public static ExifImageInfo readOrientationFromExif(InputStream is) throws IOException {

        ExifInterface exif = new ExifInterface(is);
        ExifImageInfo info = new ExifImageInfo();
        info.setRotation(0);

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                info.setRotation(0);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                info.setRotation(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                info.setRotation(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                info.setRotation(270);
                break;
            case ExifInterface.ORIENTATION_UNDEFINED:
            default:
                info.setRotation(0);
                break;
        }
        return info;
    }

    /** Get sampled bitmap.
     *  See https://developer.android.com/topic/performance/graphics/load-bitmap.html
     */
    public static Bitmap decodeSampledBitmap(File file, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    public static Bitmap decodeSampledBitmap(byte[] bytes, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    /** Get sampled bitmap from content uri.
     *  The uri should be able to be read multiple times.
     *
     * @param context
     * @param uri
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    @Nullable
    public static Bitmap decodeSampledBitmap(Context context, Uri uri, int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(is, null, options);
        } catch (IOException e) {
        } catch (SecurityException e) {
            // this exception shown on crashlytics
            // can not be reproduced locally yet
        } finally {
            close(is);
        }

        if (is == null) {
            return null;
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(is, null, options);
        } catch (IOException e) {
        } catch (SecurityException e) {
        } finally {
            close(is);
        }
        return bitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }


        return inSampleSize;
    }

}
