package com.jueyes.shoppingcartdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_STORE = 0;         // 店铺名称
    public static final int TYPE_ARTIC = 1;         // 商品详情
    public static final int TYPE_LOST_TITLE = 2;    // 失效商品标题
    public static final int TYPE_LOST_ARTIC = 3;    // 失效商品详情
    public static final int TYPE_LOST_BUTTO = 4;    // 失效商品按钮

    private ArrayList<StoreBean> storeBeanList; // 店铺集合,包含商品集合;
    private ArrayList<LostArticBean> lostArticBeanList; // 失效商品集合;
    private ArrayList<ViewBean> viewBeanList;   // 全部数据的集合
      Context context;
    /**
     * 设置店铺数据(同时设置店铺内的商品)
     *
     * @param storeBeanList
     */
    public void setStoreList(ArrayList<StoreBean> storeBeanList) {
        this.storeBeanList = storeBeanList;

        // (重要) 更新内部数据
        updateViewBeanList();
        notifyDataSetChanged();
    }

    /**
     * 设置失效商品数据
     *
     * @param lostArticBeanList
     */
    public void setLostArticBeanList(ArrayList<LostArticBean> lostArticBeanList) {
        this.lostArticBeanList = lostArticBeanList;

        // (重要) 更新内部数据
        updateViewBeanList();
        notifyDataSetChanged();
    }

    // 更新全部数据的集合,此方法一定要在数据改变后调用,并且在 notifyDataSetChanged() 方法前调用
    private void updateViewBeanList() {
        // 重新创建集合
        viewBeanList = new ArrayList<>();

        // 判断店铺集合是否有内容
        if (storeBeanList != null && storeBeanList.size() > 0) {
            // 遍历店铺集合,添加商品数量
            for (StoreBean storeBean : storeBeanList) {
                // 检测当前店铺是否有商品存在
                if (storeBean.getArticBeanList() != null && storeBean.getArticBeanList().size() > 0) {
                    // 添加店铺标题
                    viewBeanList.add(new ViewBean(TYPE_STORE,storeBean));
                    // 遍历店铺内商品集合
                    for (ArticBean articBean : storeBean.getArticBeanList()) {
                        // 添加商品详情
                        viewBeanList.add(new ViewBean(TYPE_ARTIC, articBean));
                    }
                }
            }
        }

        // 判断失效商品集合是否有内容
        if (lostArticBeanList != null && lostArticBeanList.size() > 0) {
            // 添加失效商品标题
            viewBeanList.add(new ViewBean(TYPE_LOST_TITLE));
            // 添加失效商品详情
            for (LostArticBean lostArticBean : lostArticBeanList) {
                viewBeanList.add(new ViewBean(TYPE_LOST_ARTIC, lostArticBean));
            }
            // 添加失效商品清除按钮
            viewBeanList.add(new ViewBean(TYPE_LOST_BUTTO));
        }
    }

    @Override
    public int getItemCount() {
        return viewBeanList == null ? 0 : viewBeanList.size();
        /* 此方法已经废弃
        int count = 0;

        // 判断店铺集合是否有内容
        if (storeList != null && storeList.size() > 0) {
            // 遍历店铺集合,添加商品数量
            for (StoreBean storeBean : storeList) {
                // 检测当前店铺是否有商品存在
                if (storeBean.getArticBeanList() != null && storeBean.getArticBeanList().size() > 0) {
                    // 添加店铺名称和商品详情
                    // storeBean.getArticBeanList().size():当前店铺内商品数量
                    // 1: 店铺名称
                    count += (storeBean.getArticBeanList().size() + 1);
                }
            }
        }

        // 判断失效商品集合是否有内容
        if (lostArticBeanList != null && lostArticBeanList.size() > 0) {
            // 添加失效商品数量
            // lostArticBean.size():失效商品数量
            // 2:失效商品标题和底部清空按钮
            count += (lostArticBeanList.size() + 2);
        }

        return count;
        */
    }

    @Override
    public int getItemViewType(int position) {
        return viewBeanList != null && viewBeanList.size() > position ? viewBeanList.get(position).getViewType() : -1;
        /* 这种方式已经废弃
        // 类型集合
        ArrayList<Integer> viewTypeList = new ArrayList<>();

        // 添加店铺或者正常商品
        if (storeList != null && storeList.size() > 0) {

            // 遍历店铺集合
            for (StoreBean storeBean : storeList) {

                // 检测当前店铺中是否有商品
                if (storeBean.getArticBeanList() != null && storeBean.getArticBeanList().size() > 0) {

                    // 添加店铺类型
                    viewTypeList.add(TYPE_STORE);

                    // 遍历店铺内商品集合
                    for (int i = 0; i < storeBean.getArticBeanList().size(); i++) {

                        // 添加商品类型
                        viewTypeList.add(TYPE_ARTIC);
                    }
                }
            }
        }

        // 添加已失效商品类型
        if (lostArticBeanList != null && lostArticBeanList.size() > 0) {

            // 添加失效商品标题
            viewTypeList.add(TYPE_ARTIC);

            // 遍历失效商品集合
            for (int i = 0; i < lostArticBeanList.size(); i++) {

                // 添加失效商品详情
                viewTypeList.add(TYPE_ARTIC);
            }

            // 添加失效商品清空按钮
            viewTypeList.add(TYPE_ARTIC);
        }

        return viewTypeList.get(position);
        */
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_STORE:
                return StoreViewHolder.instance(viewGroup);
            case TYPE_ARTIC:
                return ArticViewHolder.instance(viewGroup);
            case TYPE_LOST_TITLE:
                return LostTitleViewHolder.instance(viewGroup);
            case TYPE_LOST_ARTIC:
                return LostArticViewHolder.instance(viewGroup);
            case TYPE_LOST_BUTTO:
                return LostButtoViewHolder.instance(viewGroup);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof StoreViewHolder && viewBeanList.get(position).getData() instanceof StoreBean)
            ((StoreViewHolder) viewHolder).onBindView((StoreBean) viewBeanList.get(position).getData());

        if (viewHolder instanceof ArticViewHolder && viewBeanList.get(position).getData() instanceof ArticBean)
            ((ArticViewHolder) viewHolder).onBindView((ArticBean) viewBeanList.get(position).getData());
       /*
        if (viewHolder instanceof LostTitleViewHolder)
            ((LostTitleViewHolder) viewHolder).onBindView(null);
        */

        if (viewHolder instanceof LostArticViewHolder && viewBeanList.get(position).getData() instanceof LostArticBean)
            ((LostArticViewHolder) viewHolder).onBindView((LostArticBean) viewBeanList.get(position).getData());

       /*
        if (viewHolder instanceof LostButtoViewHolder)
            ((LostButtoViewHolder) viewHolder).onBindView(null);
        */
    }

    // 店铺名称 ViewHolder
    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_Title;

        // 获取 StoreViewHolder
        public static StoreViewHolder instance(ViewGroup parent) {
            return new StoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_store, parent, false));
        }

        // 构造函数
        private StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Title = itemView.findViewById(R.id.textView_StoreHolder_Title);
        }

        // 绑定数据
        public void onBindView(StoreBean storeBean) {
            textView_Title.setText(storeBean.getStoreTitle());
        }
    }

    // 商品详情 ViewHolder
    public static class ArticViewHolder extends RecyclerView.ViewHolder {

        private final View mArticView;
        private TextView textView_Title;
        private ImageView imageView_ArticHolder_Icon;
        // 获取 StoreViewHolder
        public static ArticViewHolder instance(ViewGroup parent) {
            return new ArticViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_artic, parent, false));
        }

        // 构造函数
        private ArticViewHolder(@NonNull View itemView) {
            super(itemView);
            mArticView =itemView;
            textView_Title = itemView.findViewById(R.id.textView_ArticHolder_Title);
            imageView_ArticHolder_Icon = itemView.findViewById(R.id.imageView_ArticHolder_Icon);
        }

        // 绑定数据
        public void onBindView(ArticBean articBean) {
            textView_Title.setText(articBean.getArticTitle());
            RequestOptions options = new RequestOptions();
            GlideUtils.glideImage(mArticView,articBean.getArticIcon(),imageView_ArticHolder_Icon,options);
//            Glide.with(mArticView).load(articBean.getArticIcon()).into(imageView_ArticHolder_Icon);
        }
    }

    // 失效商品标题 ViewHolder
    public static class LostTitleViewHolder extends RecyclerView.ViewHolder {

        // 获取 StoreViewHolder
        public static LostTitleViewHolder instance(ViewGroup parent) {
            return new LostTitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_lost_title, parent, false));
        }

        // 构造函数
        private LostTitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    // 失效商品详情 ViewHolder
    public static class LostArticViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private TextView textView_Title;
        private ImageView imageView_Icon;
        // 获取 StoreViewHolder
        public static LostArticViewHolder instance(ViewGroup parent) {

            return new LostArticViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_lost_artic, parent, false));
        }

        // 构造函数
        private LostArticViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            textView_Title = itemView.findViewById(R.id.textView_LostArticHolder_Title);
            imageView_Icon = itemView.findViewById(R.id.imageView_LostArticHolder_Icon);
        }

        // 绑定数据
        public void onBindView(LostArticBean lostArticBean) {
            textView_Title.setText(lostArticBean.getArticTitle());
            Glide.with(mView).load(lostArticBean.getArticIcon()).into(imageView_Icon);
        }
    }

    // 失效商品清空按钮 ViewHolder
    public static class LostButtoViewHolder extends RecyclerView.ViewHolder {

        // 获取 StoreViewHolder
        public static LostButtoViewHolder instance(ViewGroup parent) {
            return new LostButtoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_lost_butto, parent, false));
        }

        // 构造函数
        private LostButtoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    // 对数据进行封装
    private static class ViewBean {
        private int viewType;
        private Object data;

        public ViewBean(int viewType) {
            this(viewType, null);
        }

        public ViewBean(int viewType, Object data) {
            this.viewType = viewType;
            this.data = data;
        }

        public int getViewType() {
            return viewType;
        }

        public Object getData() {
            return data;
        }
    }

    // 店铺数据
    public static class StoreBean {
        private String storeTitle;    // 店铺名称
        private ArrayList<ArticBean> articBeanList;

        public String getStoreTitle() {
            return storeTitle;
        }

        public void setStoreTitle(String storeTitle) {
            this.storeTitle = storeTitle;
        }

        public ArrayList<ArticBean> getArticBeanList() {
            return articBeanList;
        }

        public void setArticBeanList(ArrayList<ArticBean> articBeanList) {
            this.articBeanList = articBeanList;
        }
    }

    // 商品数据
    public static class ArticBean {
        private int overseas;
        private String articIcon;
        private String articTitle;
        private String articPriceA;
        private String articPriceB;
        private int articNumber;
        private boolean articPitch;  // 选中状态(默认为false)

        public int getOverseas() {
            return overseas;
        }

        public void setOverseas(int overseas) {
            this.overseas = overseas;
        }

        public String getArticIcon() {
            return articIcon;
        }

        public void setArticIcon(String articIcon) {
            this.articIcon = articIcon;
        }

        public String getArticTitle() {
            return articTitle;
        }

        public void setArticTitle(String articTitle) {
            this.articTitle = articTitle;
        }

        public String getArticpriceA() {
            return articPriceA;
        }

        public void setArticpriceA(String articpriceA) {
            this.articPriceA = articpriceA;
        }

        public String getArticpriceB() {
            return articPriceB;
        }

        public void setArticpriceB(String articpriceB) {
            this.articPriceB = articpriceB;
        }

        public int getArticNumber() {
            return articNumber;
        }

        public void setArticNumber(int articNumber) {
            this.articNumber = articNumber;
        }

        public boolean isArticPitch() {
            return articPitch;
        }

        public void setArticPitch(boolean articPitch) {
            this.articPitch = articPitch;
        }
    }

    // 失效商品数据
    public static class LostArticBean {

        private String articIcon;
        private String articTitle;
        private String articpriceA;
        private String articpriceB;
        private int articNumber;

        public String getArticIcon() {
            return articIcon;
        }

        public void setArticIcon(String articIcon) {
            this.articIcon = articIcon;
        }

        public String getArticTitle() {
            return articTitle;
        }

        public void setArticTitle(String articTitle) {
            this.articTitle = articTitle;
        }

        public String getArticpriceA() {
            return articpriceA;
        }

        public void setArticpriceA(String articpriceA) {
            this.articpriceA = articpriceA;
        }

        public String getArticpriceB() {
            return articpriceB;
        }

        public void setArticpriceB(String articpriceB) {
            this.articpriceB = articpriceB;
        }

        public int getArticNumber() {
            return articNumber;
        }

        public void setArticNumber(int articNumber) {
            this.articNumber = articNumber;
        }
    }
}
