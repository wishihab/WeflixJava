package com.wishihab.weflixjava.util.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wishihab.weflixjava.model.core.ExifImageInfo;


public final class ImageUtil {


    private static final int SMALL_THUMB_WIDTH  = 144; // px
    private static final int SMALL_THUMB_HEIGHT = 144; // px

    private static final int MEDIUM_THUMB_WIDTH  = 256; // px
    private static final int MEDIUM_THUMB_HEIGHT = 256; // px

    private static final int LARGE_RECTANGLE_WIDTH  = 720; // px
    private static final int LARGE_RECTANGLE_HEIGHT = 540; // px

    private static final int MEDIUM_LOCAL_WIDTH  = 800; // px
    private static final int MEDIUM_LOCAL_HEIGHT = 800; // px

    private static final int BANNER_WIDTH = 800; // px
    private static final int BANNER_HEIGHT = 400; // px

    private static final int BANNER_LARGE_WIDTH  = 1024; // px
    private static final int BANNER_LARGE_HEIGHT =  512; // px

    /** Muat gambar dengan ukuran asli dari URL apa adanya.
     *
     *  WARNING: may cause {@link OutOfMemoryError} because image is not scaled.
     *  It is recommended to use {@link #loadImage(ImageView, int, int, String, int)} instead.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param url
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImage(
            @NonNull ImageView imageView,
            @Nullable String url,
            int placeholderId) {

        if (isEmpty(url)) {
            imageView.setImageResource(placeholderId);
            return;
        }

        Picasso.get()
                .load(url)
                .placeholder(placeholderId)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView);
    }

    /** Memuat gambar dengan disesuikan ke ukuran width dan height yang diberikan,
     *  penampilan gambar menggunakan center inside.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param maxWidth
     *      Maksimum ukuran lebar gambar saat ditampilkan.
     * @param maxHeight
     *      Maksimum ukuran tinggi gambar saat ditampilkan.
     * @param url
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImage(
            @NonNull ImageView imageView,
            int maxWidth,
            int maxHeight,
            @Nullable String url,
            int placeholderId) {

        if (isEmpty(url)) {
            imageView.setImageResource(placeholderId);
            return;
        }
        Picasso.get()
                .load(url)
                .resize(maxWidth, maxHeight)
                .centerInside()
                .onlyScaleDown()
                .placeholder(placeholderId)
                .into(imageView);
    }

    /**  Memuat gambar dengan ukuran kecil, penampilan gambar menggunakan center inside.
     *
     */
    public static void loadImageSmall(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        loadImage(imageView, SMALL_THUMB_WIDTH, SMALL_THUMB_HEIGHT, uri, placeholderId);
    }

    /** Memuat gambar dengan ukuran sedang, penampilan gambar menggunakan center inside.
     *
     */
    public static void loadImageMedium(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        loadImage(imageView, MEDIUM_THUMB_WIDTH, MEDIUM_THUMB_HEIGHT, uri, placeholderId);
    }

    /** Memuat gambar dengan penampilan gambar menggunakan center crop.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param maxWidth
     *      Maksimum ukuran lebar gambar saat ditampilkan.
     * @param maxHeight
     *      Maksimum ukuran tinggi gambar saat ditampilkan.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImageCenterCrop(
            @NonNull ImageView imageView,
            int maxWidth,
            int maxHeight,
            @Nullable String uri,
            int placeholderId) {

        if (isEmpty(uri)) {
            imageView.setImageResource(placeholderId);
            return;
        }

        Picasso.get()
                .load(uri)
                .resize(maxWidth, maxHeight)
                .onlyScaleDown()
                .centerCrop()
                .placeholder(placeholderId)
                .into(imageView);
    }

    /** Memuat gambar ukuran kecil dengan penampilan gambar menggunakan center crop.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImageCenterCropSmall(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        loadImageCenterCrop(imageView, SMALL_THUMB_WIDTH, SMALL_THUMB_HEIGHT, uri, placeholderId);
    }

    /** Memuat gambar ukuran sedang dengan penampilan gambar menggunakan center crop.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImageCenterCropMedium(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        loadImageCenterCrop(imageView, MEDIUM_THUMB_WIDTH, MEDIUM_THUMB_HEIGHT, uri, placeholderId);
    }

    /** Muat gambar besar yang diresize untuk list item, menggunakan center crop ke container persegi.
     *  Bukan untuk container yang kotak.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImageRectangleLarge(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        loadImageCenterCrop(imageView, LARGE_RECTANGLE_WIDTH, LARGE_RECTANGLE_HEIGHT, uri, placeholderId);
    }

    /** Memuat gambar dengan penampilan gambar menggunakan center crop dan transformasi circle.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param url
     *      Alamat gambar yang akan dimuat.
     * @param maxWidth
     *      Maksimum ukuran lebar gambar saat ditampilkan.
     * @param maxHeight
     *      Maksimum ukuran tinggi gambar saat ditampilkan.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadImageCircle(
            @NonNull ImageView imageView,
            int maxWidth,
            int maxHeight,
            @Nullable String url,
            int placeholderId) {

        if (url == null) {
            imageView.setImageResource(placeholderId);
            return;
        }

        Picasso.get()
                .load(url)
                .placeholder(placeholderId)
                .resize(maxWidth, maxHeight)
                .transform(new PicassoCircleTransfrom())
                .centerCrop()
                .onlyScaleDown()
                .into(imageView);
    }

    /** Memuat gambar ke ukuran kecil dengan tampilan gambar center crop dan transformasi circle.
     */
    public static void loadImageCircleSmall(@NonNull ImageView imageView, @Nullable String url, int placeholderId) {
        loadImageCircle(imageView, SMALL_THUMB_WIDTH, SMALL_THUMB_HEIGHT, url, placeholderId);
    }

    /** Memuat gambar ke ukuran medium dengan tampilan gambar center crop dan transformasi circle.
     */
    public static void loadImageCircleMedium(@NonNull ImageView imageView, @Nullable String url, int placeholderId) {
        loadImageCircle(imageView, MEDIUM_THUMB_WIDTH, MEDIUM_THUMB_HEIGHT, url, placeholderId);
    }

    /** Memuat gambar khusus untuk banner, ukuran disesuaikan tergantung ukuran lebar layar, gambar center inside.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadBanner(@NonNull ImageView imageView, @Nullable String uri, int placeholderId) {
        if (isEmpty(uri)) {
            imageView.setImageResource(placeholderId);
            return;
        }

        DisplayMetrics metrics = imageView.getResources().getDisplayMetrics();
        // may be in landscape mode, so we looking for smallest for width
        int screenWidth = Math.min(metrics.widthPixels, metrics.heightPixels);

        // default banner size
        int w = BANNER_WIDTH;
        int h = BANNER_HEIGHT;

        // use larger image on screen with more pixel
        // we are not using values-xxhdpi here because we are comparing actual pixels
        if (screenWidth > BANNER_WIDTH) {
            w = BANNER_LARGE_WIDTH;
            h = BANNER_LARGE_HEIGHT;
        }

        loadImage(imageView, w, h, uri, placeholderId);
    }

    /** Memuat gambar untuk ditampilkan dengan ukuran penampilan medium, orientasi akan diperbaiki sesuai EXIF.
     *  TIDAK BOLEH digunakan untuk RecyclerView, ListView dan sejenisnya.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadLocalImageMedium(@NonNull ImageView imageView, @NonNull Uri uri, int placeholderId) {
        loadLocalImage(imageView, MEDIUM_LOCAL_WIDTH, MEDIUM_LOCAL_HEIGHT, uri, placeholderId);
    }

    /** Memuat gambar untuk ditampilkan, orientasi akan diperbaiki sesuai EXIF.
     *  TIDAK BOLEH digunakan untuk RecyclerView, ListView dan sejenisnya.
     *
     * @param imageView
     *      Target ImageView untuk menampilkan gambar.
     * @param uri
     *      Alamat gambar yang akan dimuat.
     * @param placeholderId
     *      Resources ID dari drawable saat memuat gambar / gagal meload gambar.
     */
    public static void loadLocalImage(@NonNull ImageView imageView, int maxWidth, int maxHeight, @Nullable Uri uri, int placeholderId) {
        if (isEmpty(uri)) {
            imageView.setImageResource(placeholderId);
            return;
        }
        ScaledImageLoader imageLoader = new ScaledImageLoader(imageView, maxWidth, maxHeight, uri, placeholderId);
        imageLoader.start();

        // picasso 2.5.2 will rotate according to exif  if content uri come from MediaStore
        // but won't rotate when content uri come from file provider content:// uri
        // (such as when using camera app)
        // so we rotate it manually using ScaledImageLoader
        /*
        Picasso.with(imageView.getContext())
                .load(uri)
                .resize(SCALED_WIDTH, SCALED_HEIGHT)
                .centerCrop()
                .placeholder(placeholderId)
                .into(imageView);
                */
    }

    /** Muat gambar dan panggil consumer dengan hasil bitmap, atau null jika gagal meload gambar.
     *
     */
    public static void loadImage(@Nullable String url, @NonNull Consumer<Bitmap> consumer) {
        if (isEmpty(url)) {
            consumer.accept(null);
            return;
        }
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        consumer.accept(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        consumer.accept(null);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    /** Muat gambar dan panggil consumer dengan hasil bitmap, atau null jika gagal meload gambar.
     *
     * @param url
     *      Lokasi gambar
     * @param width
     *      Ukuran resize panjang.
     * @param height
     *      Ukuran resize tinggi gambar.
     * @param consumer
     *      Callback yang akan dipanggil saat selesai memuat gambar.
     */
    public static void loadImage(@Nullable String url, int width, int height, @NonNull Consumer<Bitmap> consumer) {
        if (isEmpty(url)) {
            consumer.accept(null);
            return;
        }
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(width, height)
                .centerInside()
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        consumer.accept(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        consumer.accept(null);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private static boolean isEmpty(String url) {
        return url == null || url.length() == 0;
    }

    private static boolean isEmpty(Uri url) {
        return url == null || url.toString().length() == 0;
    }
}

/** Task untuk menyesuaikan ukuran gambar.
 *
 */
final class ScaledImageLoader implements Runnable {


    private final ImageView imageView;
    private final int maxWidth;
    private final int maxHeight;
    private final Uri uri;
    private final int placeholder;

    private final Handler handler;

    public ScaledImageLoader(@NonNull ImageView imageView, int maxWidth, int maxHeight, @NonNull Uri uri, int placeholder) {
        this.imageView = imageView;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.placeholder = placeholder;
        this.uri = uri;
        handler = new Handler(Looper.getMainLooper());
        imageView.setImageResource(placeholder);
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Bitmap bitmap = scale();
        handler.post(()->post(bitmap));
    }

    private void post(Bitmap bitmap) {
        if (bitmap == null) {
            imageView.setImageResource(placeholder);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap scale() {
        final Context context = imageView.getContext();
        Bitmap bitmap = BitmapUtils.decodeSampledBitmap(context, uri, maxWidth, maxHeight);
        if (bitmap == null) {
            return null;
        }

        ExifImageInfo info = BitmapUtils.readOrientationFromExif(context, uri);
        int rotation = info.getRotation();
        if (rotation != 0) {
            bitmap = rotateBitmap(bitmap, rotation);
        }
        return bitmap;
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        bitmap = result;
        return bitmap;
    }

}