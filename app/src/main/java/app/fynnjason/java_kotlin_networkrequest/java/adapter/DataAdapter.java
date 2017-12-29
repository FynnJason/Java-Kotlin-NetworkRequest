package app.fynnjason.java_kotlin_networkrequest.java.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.fynnjason.java_kotlin_networkrequest.R;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;

/**
 * Created by FynnJason.
 * Des：
 */

public class DataAdapter extends BaseQuickAdapter<GankBean,BaseViewHolder> {
    public DataAdapter(@Nullable List<GankBean> data) {
        super(R.layout.item_gank,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,GankBean item) {
        helper.setText(R.id.tv_id, item.get_id())
                .setText(R.id.tv_createdAt, item.getCreatedAt())
                .setText(R.id.tv_des, item.getDesc())
                .setText(R.id.tv_publishedAt, item.getPublishedAt())
                .setText(R.id.tv_source, item.getSource())
                .setText(R.id.tv_type, item.getType())
                .setText(R.id.tv_url, item.getUrl())
                .setText(R.id.tv_used, item.isUsed() + "")
                .setText(R.id.tv_who, item.getWho());
    }
}
