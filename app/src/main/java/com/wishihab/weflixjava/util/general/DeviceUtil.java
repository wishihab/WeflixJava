package com.wishihab.weflixjava.util.general;

import android.content.Context;
import android.content.res.Configuration;

public final class DeviceUtil {

    public static boolean isPortraitOrientation(Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
