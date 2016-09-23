package com.cyq7on.template.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cyq7on.template.R;

/**
 * @Description:
 * @author: cyq7on
 * @date: 2016/9/22 0022 16:18
 * @version: V1.0
 */
public class ImageUtils {
    public static void displayImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().
                placeholder(R.drawable.def_head).
//                transform(new GlideCircleTransform(context)).
                into(imageView);
    }
}
