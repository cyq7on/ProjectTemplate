package com.cyq7on.template.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cyq7on.template.R;
import com.cyq7on.template.bean.HomeInfo;
import com.cyq7on.template.utils.ImageUtils;

import java.util.List;

/**
 * @Description:
 * @author: cyq7on
 * @date: 2016/8/1 10:41
 * @version: V1.0
 */
public class HomeAdapter extends BaseQuickAdapter<HomeInfo.ResultBean.DataBean>{
    public HomeAdapter(List<HomeInfo.ResultBean.DataBean> data) {
        super(R.layout.item_home,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeInfo.ResultBean.DataBean bean) {
        baseViewHolder.setText(R.id.tv,bean.title);
        ImageView imageView = baseViewHolder.getView(R.id.iv);
        ImageUtils.displayImage(mContext,bean.thumbnail_pic_s,imageView);
    }
}
