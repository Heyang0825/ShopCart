package com.jueyes.shoppingcartdemo;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_Content;
    private MainAdapter adapter;
    ArrayList<MainAdapter.LostArticBean> lostArticBeanList = new ArrayList<>();
    // 添加商铺数据
    ArrayList<MainAdapter.StoreBean> storeBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取控件
        recyclerView_Content = findViewById(R.id.recyclerView_MainActivity_Content);

        // 初始化 RecyclerView
        recyclerView_Content.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_Content.setAdapter(adapter = new MainAdapter());

        getCartData();


        // 添加失效商品数据
//        ArrayList<MainAdapter.LostArticBean> lostArticBeanList = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            MainAdapter.LostArticBean lostArticBean = new MainAdapter.LostArticBean();
//            lostArticBean.setArticTitle("第" + i + "个失效商品");
//            lostArticBeanList.add(lostArticBean);
//        }

//        adapter.setLostArticBeanList(lostArticBeanList);
    }

    public void getCartData() {
        String path = "http://preview.web.qqsk.com/shoppingCart/select_all";
        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder().add("", "").build();
        Request request = new Request.Builder().addHeader("Cookie", "TOKEN=d9ae7d2e-610d-4810-8434-f95ab1f83c789433473; ONEAPM_BI_sessionid=5126.513|1543397965022; Hm_lvt_d24158fffc788dc4387beabdadcf77d3=1543392237").get().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                JSONObject jsonObject = JSONObject.parseObject("{\n" +
                        "\t\"status\": 200,\n" +
                        "\t\"msg\": \"ok\",\n" +
                        "\t\"debugMsg\": null,\n" +
                        "\t\"data\": {\n" +
                        "\t\t\"pageSize\": 1,\n" +
                        "\t\t\"pageList\": [{\n" +
                        "\t\t\t\t\"productId\": 17803,\n" +
                        "\t\t\t\t\"skucode\": \"71023956\",\n" +
                        "\t\t\t\t\"spucode\": \"5500357690\",\n" +
                        "\t\t\t\t\"spuId\": 10231,\n" +
                        "\t\t\t\t\"productName\": \"iphone新品预售/apple iphone XS  64G 深空灰\",\n" +
                        "\t\t\t\t\"importDuties\": \"0.00\",\n" +
                        "\t\t\t\t\"productImageUrl\": \"https://image.qqsk.com/productImage/1536905504213.jpg\",\n" +
                        "\t\t\t\t\"price\": \"8699.00\",\n" +
                        "\t\t \n" +
                        "\t\t\t\t\"priceActivity\": 8699.0,\n" +
                        "\t\t\t\t\"priced\": 8699.0,\n" +
                        "\t\t\t\t\"soldNum\": 4,\n" +
                        "\t\t\t\t\"stockNumber\": 0,\n" +
                        "\t\t\t\t\"stockNumberActivity\": 2,\n" +
                        "\t\t\t\t\"prop\": \"深空灰、64G\",\n" +
                        "\t\t\t\t\"warehouseCode\": \"HZZSDZKJ\",\n" +
                        "\t\t\t\t\"refund\": 0,\n" +
                        "\t\t\t\t\"rebateCode\": 0.0,\n" +
                        "\t\t\t\t\"maxPurchase\": 9,\n" +
                        "\t\t\t\t\"minPurchase\": 1,\n" +
                        "\t\t\t\t\"maxPurchaseAct\": 9,\n" +
                        "\t\t\t\t\"minPurchaseAct\": 1,\n" +
                        "\t\t\t\t\"isRelease\": 1,\n" +
                        "\t\t\t\t\"haveCarriage\": 0,\n" +
                        "\t\t\t\t\"cartId\": 1181081,\n" +
                        "\t\t\t\t\"shopName\": \"全球时刻\",\n" +
                        "\t\t\t\t\"warehouseShortName\": null,\n" +
                        "\t\t\t\t\"num\": 1,\n" +
                        "\t\t\t\t\"activityIds\": [1408],\n" +
                        "\t\t\t\t\"activitys\": [],\n" +
                        "\t\t\t\t\"overseas\": 0,\n" +
                        "\t\t\t\t\"joinActivityNum\": null,\n" +
                        "\t\t\t\t\"salePriceOne\": null,\n" +
                        "\t\t\t\t\"salePriceAll\": null,\n" +
                        "\t\t\t\t\"couponSalePrice\": null,\n" +
                        "\t\t\t\t\"saleGoldOne\": null,\n" +
                        "\t\t\t\t\"saleGoldAll\": null,\n" +
                        "\t\t\t\t\"memberRole\": null,\n" +
                        "\t\t\t\t\"isUpdateMember\": null,\n" +
                        "\t\t\t\t\"byMember\": null,\n" +
                        "\t\t\t\t\"f\": false,\n" +
                        "\t\t\t\t\"isVirtualBom\": 0,\n" +
                        "\t\t\t\t\"ferme\": 0\n" +
                        "\t\t\t}, {\n" +
                        "\t\t\t\t\"productId\": 17810,\n" +
                        "\t\t\t\t\"skucode\": \"71023959\",\n" +
                        "\t\t\t\t\"spucode\": \"5500357690\",\n" +
                        "\t\t\t\t\"spuId\": 10231,\n" +
                        "\t\t\t\t\"productName\": \"iphone新品预售/apple iphone XS  256G 深空灰\",\n" +
                        "\t\t\t\t\"importDuties\": \"0.00\",\n" +
                        "\t\t\t\t\"productImageUrl\": \"https://image.qqsk.com/productImage/1536905826591.jpg\",\n" +
                        "\t\t\t\t\"price\": \"10099.00\",\n" +
                        "\t\t\t\t\"priceActivity\": 10099.0,\n" +
                        "\t\t\t\t\"priced\": 10099.0,\n" +
                        "\t\t\t\t\"soldNum\": 5,\n" +
                        "\t\t\t\t\"stockNumber\": 0,\n" +
                        "\t\t\t\t\"stockNumberActivity\": 0,\n" +
                        "\t\t\t\t\"prop\": \"深空灰、256G\",\n" +
                        "\t\t\t\t\"warehouseCode\": \"HZZSDZKJ\",\n" +
                        "\t\t\t\t\"refund\": 0,\n" +
                        "\t\t\t\t\"rebateCode\": 0.0,\n" +
                        "\t\t\t\t\"maxPurchase\": 9,\n" +
                        "\t\t\t\t\"minPurchase\": 1,\n" +
                        "\t\t\t\t\"maxPurchaseAct\": 9,\n" +
                        "\t\t\t\t\"minPurchaseAct\": 1,\n" +
                        "\t\t\t\t\"isRelease\": 1,\n" +
                        "\t\t\t\t\"haveCarriage\": 0,\n" +
                        "\t\t\t\t\"cartId\": 1181776,\n" +
                        "\t\t\t\t\"shopName\": \"全球时刻优选商城\",\n" +
                        "\t\t\t\t\"warehouseShortName\": null,\n" +
                        "\t\t\t\t\"num\": 1,\n" +
                        "\t\t\t\t\"activityIds\": [1408],\n" +
                        "\t\t\t\t\"activitys\": [],\n" +
                        "\t\t\t\t\"overseas\": 0,\n" +
                        "\t\t\t\t\"joinActivityNum\": null,\n" +
                        "\t\t\t\t\"salePriceOne\": null,\n" +
                        "\t\t\t\t\"salePriceAll\": null,\n" +
                        "\t\t\t\t\"couponSalePrice\": null,\n" +
                        "\t\t\t\t\"saleGoldOne\": null,\n" +
                        "\t\t\t\t\"saleGoldAll\": null,\n" +
                        "\t\t\t\t\"memberRole\": null,\n" +
                        "\t\t\t\t\"isUpdateMember\": null,\n" +
                        "\t\t\t\t\"byMember\": null,\n" +
                        "\t\t\t\t\"f\": false,\n" +
                        "\t\t\t\t\"isVirtualBom\": 0,\n" +
                        "\t\t\t\t\"ferme\": 0\n" +
                        "\t\t\t},\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\t\"productId\": 6798,\n" +
                        "\t\t\t\t\"skucode\": \"DX8015194101237\",\n" +
                        "\t\t\t\t\"spucode\": \"510000326\",\n" +
                        "\t\t\t\t\"spuId\": 4272,\n" +
                        "\t\t\t\t\"productName\": \"大公鸡 公鸡头管家 白麝香洗衣液(白茉莉）1000ml\",\n" +
                        "\t\t\t\t\"importDuties\": \"0.00\",\n" +
                        "\t\t\t\t\"productImageUrl\": \"https://image.qqsk.com/productImage/1517986463973.jpg\",\n" +
                        "\t\t\t\t\"price\": \"39.00\",\n" +
                        "\t\t\t\t\"priceActivity\": 24.9,\n" +
                        "\t\t\t\t\"priced\": 39.0,\n" +
                        "\t\t\t\t\"soldNum\": 2267,\n" +
                        "\t\t\t\t\"stockNumber\": 601,\n" +
                        "\t\t\t\t\"stockNumberActivity\": 606,\n" +
                        "\t\t\t\t\"prop\": \"白麝香（白苔香）1000ml\",\n" +
                        "\t\t\t\t\"warehouseCode\": \"QQSK\",\n" +
                        "\t\t\t\t\"refund\": 1,\n" +
                        "\t\t\t\t\"rebateCode\": 0.1,\n" +
                        "\t\t\t\t\"maxPurchase\": 9,\n" +
                        "\t\t\t\t\"minPurchase\": 1,\n" +
                        "\t\t\t\t\"maxPurchaseAct\": 9,\n" +
                        "\t\t\t\t\"minPurchaseAct\": 1,\n" +
                        "\t\t\t\t\"isRelease\": 1,\n" +
                        "\t\t\t\t\"haveCarriage\": 1,\n" +
                        "\t\t\t\t\"cartId\": 1305299,\n" +
                        "\t\t\t\t\"shopName\": \"全球时刻优选商城\",\n" +
                        "\t\t\t\t\"warehouseShortName\": null,\n" +
                        "\t\t\t\t\"num\": 1,\n" +
                        "\t\t\t\t\"activityIds\": [],\n" +
                        "\t\t\t\t\"activitys\": [],\n" +
                        "\t\t\t\t\"overseas\": 0,\n" +
                        "\t\t\t\t\"joinActivityNum\": null,\n" +
                        "\t\t\t\t\"salePriceOne\": null,\n" +
                        "\t\t\t\t\"salePriceAll\": null,\n" +
                        "\t\t\t\t\"couponSalePrice\": null,\n" +
                        "\t\t\t\t\"saleGoldOne\": null,\n" +
                        "\t\t\t\t\"saleGoldAll\": null,\n" +
                        "\t\t\t\t\"memberRole\": null,\n" +
                        "\t\t\t\t\"isUpdateMember\": null,\n" +
                        "\t\t\t\t\"byMember\": null,\n" +
                        "\t\t\t\t\"f\": false,\n" +
                        "\t\t\t\t\"isVirtualBom\": 0,\n" +
                        "\t\t\t\t\"ferme\": 0\n" +
                        "\t\t\t}, {\n" +
                        "\t\t\t\t\"productId\": 13935,\n" +
                        "\t\t\t\t\"skucode\": \"8015194512095\",\n" +
                        "\t\t\t\t\"spucode\": \"500231109\",\n" +
                        "\t\t\t\t\"spuId\": 8458,\n" +
                        "\t\t\t\t\"productName\": \"公鸡头管家厨卫专用清洁剂500ML\",\n" +
                        "\t\t\t\t\"importDuties\": \"0.00\",\n" +
                        "\t\t\t\t\"productImageUrl\": \"https://image.qqsk.com/productImage/1539854841890.jpg\",\n" +
                        "\t\t\t\t\"price\": \"29.90\",\n" +
                        "\t\t\t\t\"priceActivity\": 28.0,\n" +
                        "\t\t\t\t\"priced\": 29.9,\n" +
                        "\t\t\t\t\"soldNum\": 388,\n" +
                        "\t\t\t\t\"stockNumber\": 937,\n" +
                        "\t\t\t\t\"stockNumberActivity\": 980,\n" +
                        "\t\t\t\t\"prop\": \"清洁剂500ML\",\n" +
                        "\t\t\t\t\"warehouseCode\": \"QQSK\",\n" +
                        "\t\t\t\t\"refund\": 1,\n" +
                        "\t\t\t\t\"rebateCode\": 0.06,\n" +
                        "\t\t\t\t\"maxPurchase\": 9,\n" +
                        "\t\t\t\t\"minPurchase\": 1,\n" +
                        "\t\t\t\t\"maxPurchaseAct\": 9,\n" +
                        "\t\t\t\t\"minPurchaseAct\": 1,\n" +
                        "\t\t\t\t\"isRelease\": 1,\n" +
                        "\t\t\t\t\"haveCarriage\": 1,\n" +
                        "\t\t\t\t\"cartId\": 1305308,\n" +
                        "\t\t\t\t\"shopName\": \"全球时刻优选商城\",\n" +
                        "\t\t\t\t\"warehouseShortName\": null,\n" +
                        "\t\t\t\t\"num\": 1,\n" +
                        "\t\t\t\t\"activityIds\": [2707],\n" +
                        "\t\t\t\t\"activitys\": [],\n" +
                        "\t\t\t\t\"overseas\": 0,\n" +
                        "\t\t\t\t\"joinActivityNum\": null,\n" +
                        "\t\t\t\t\"salePriceOne\": null,\n" +
                        "\t\t\t\t\"salePriceAll\": null,\n" +
                        "\t\t\t\t\"couponSalePrice\": null,\n" +
                        "\t\t\t\t\"saleGoldOne\": null,\n" +
                        "\t\t\t\t\"saleGoldAll\": null,\n" +
                        "\t\t\t\t\"memberRole\": null,\n" +
                        "\t\t\t\t\"isUpdateMember\": null,\n" +
                        "\t\t\t\t\"byMember\": null,\n" +
                        "\t\t\t\t\"f\": false,\n" +
                        "\t\t\t\t\"isVirtualBom\": 0,\n" +
                        "\t\t\t\t\"ferme\": 0\n" +
                        "\t\t\t}\n" +
                        "\t\t]\n" +
                        "\t}\n" +
                        "}");
                String status = jsonObject.getString("status");
                String msg = jsonObject.getString("msg");

                String data = jsonObject.getString("data");
                JSONObject mDataJsonObject = JSONObject.parseObject(data);
                String pageSize = mDataJsonObject.getString("pageSize");
                JSONArray pageList = mDataJsonObject.getJSONArray("pageList");
                int size = pageList.size();
                for (int i = 0; i < size; i++) {
                    String item = pageList.get(i).toString();
                    CartListJavaBean cartListJavaBean = JSON.parseObject(item, CartListJavaBean.class);
                    cartListJavaBean.getShopName();
                    Log.e("HyCartData", cartListJavaBean.getShopName());
                    int isRelease = cartListJavaBean.getIsRelease();//判断是否是失效商品 1是否 0是
                    if (isRelease == 0) {
                        MainAdapter.LostArticBean lostArticBean = new MainAdapter.LostArticBean();
                        lostArticBean.setArticTitle(cartListJavaBean.getProductName());
                        lostArticBean.setArticIcon(cartListJavaBean.getProductImageUrl());
                        lostArticBeanList.add(lostArticBean);
                    } else {
                        MainAdapter.StoreBean storeBean = new MainAdapter.StoreBean();
                        storeBean.setStoreTitle(cartListJavaBean.getShopName());
                        ArrayList<MainAdapter.ArticBean> articBeanList = new ArrayList<>();

                        MainAdapter.ArticBean articBean = new MainAdapter.ArticBean();
                        articBean.setArticTitle(cartListJavaBean.getProductName());
                        articBean.setArticIcon(cartListJavaBean.getProductImageUrl());
                        articBean.setOverseas(cartListJavaBean.getOverseas());
                        articBeanList.add(articBean);

                        storeBean.setArticBeanList(articBeanList);
                        storeBeanList.add(storeBean);
                    }
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setLostArticBeanList(lostArticBeanList);
                                adapter.setStoreList(storeBeanList);
                            }
                        });
                    }
                }).start();

            }
        });

    }
}
