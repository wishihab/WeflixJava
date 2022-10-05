package com.wishihab.weflixjava.util.core;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.ThumbnailUtils;

import com.squareup.picasso.Transformation;


public class PicassoCircleTransfrom implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        if (source == null) {
            return null;
        }

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // distorted aspect:
        /*
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        if (squared != source) {
            source.recycle();
        }
        */

        Bitmap squared = ThumbnailUtils.extractThumbnail(source, size, size);
        if (squared != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squared.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
