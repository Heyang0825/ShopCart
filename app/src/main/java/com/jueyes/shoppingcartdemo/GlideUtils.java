package com.jueyes.shoppingcartdemo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {
    public static void  glideImage(View context, String url, ImageView imageView, RequestOptions options){
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
