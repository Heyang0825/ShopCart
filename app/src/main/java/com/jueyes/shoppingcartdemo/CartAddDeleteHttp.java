package com.jueyes.shoppingcartdemo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : Heyang
 * e-mail : 15258836173@163.com
 * date   : 2018/11/3017:17
 * desc   :
 * version: 1.0
 */
public class CartAddDeleteHttp {

    private static final String HYLOG = "hy";

    /**
     * 添加购物车商品数量
     *
     * @param url        addCartNum
     * @param cartId     购物车id
     * @param productNum 商品数量
     */
    public static void addCartGoodsNum(String url, String cartId, String productNum, String mCookie) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("cartId", cartId).add("productNum", productNum).build();
        Request request = new Request.Builder().addHeader("Cookie", mCookie).post(requestBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(HYLOG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(HYLOG, "onResponse: " + response.body().string().toString());
            }
        });
    }

    /**
     * 删除 购物车商品
     *
     * @param url     /shoppingCart/delCart
     * @param cartIds 购物车id集合 多个id直接用","隔开
     * @param mCookie
     */
    public static void removeGoods(String url, String cartIds, String mCookie) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("cartIds", cartIds).build();
        Request request = new Request.Builder().addHeader("Cookie", mCookie).post(requestBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(HYLOG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(HYLOG, "onResponse: " + response.body().string().toString());
            }
        });
    }

    /**
     * 购物车提交订单
     *
     * @param orderDetRec
     * @param cartId
     * @param mCookie
     */
    public static void shopCartCommitOrder(final String orderDetRec, final String cartId, final String mCookie, String url, final Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
//            RequestBody  requestBody = new FormBody.Builder().add("Cookie",mCookie).build();
        Request request = new Request.Builder().addHeader("Cookie", mCookie).get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //在这里获取用户ID
                Intent intent = new Intent();
                intent.putExtra("mCookie", mCookie);
                intent.putExtra("masterShopId", "");
                intent.putExtra("orderDetRec", orderDetRec);
                intent.putExtra("cartId", cartId);
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

}
