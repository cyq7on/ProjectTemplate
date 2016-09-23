package com.cyq7on.template.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rey.material.app.BottomSheetDialog;
import com.cyq7on.template.R;
import com.cyq7on.template.bean.BottomType;

import java.util.ArrayList;

/**
 * Created by HelloWorld on 2016/7/19.
 */
public class BottomFragment {

    private GridView gridView;
    private ImageView btnClose;
    private BottomSheetDialog bottomSheetDialog;
    private BottomAdapter bottomAdapter;
    private Context mContext;
    private ArrayList<BottomType> bottomTypes;

    public BottomFragment(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(R.layout.bottom_fragment);
        btnClose = (ImageView) bottomSheetDialog.findViewById(R.id.close_bottom_fragment);
        gridView = (GridView) bottomSheetDialog.findViewById(R.id.content_gridview);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });

    }

    public void initData(ArrayList<BottomType> bottomTypes) {
        this.bottomTypes = bottomTypes;
        bottomAdapter = new BottomAdapter();
        gridView.setAdapter(bottomAdapter);
        bottomSheetDialog.show();
    }


    class BottomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bottomTypes.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_fragment_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.actionImg = (ImageView) convertView.findViewById(R.id.bottom_fragment_item_img);
                viewHolder.actionName = (TextView) convertView.findViewById(R.id.bottom_fragment_item_action_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            BottomType bottomType = bottomTypes.get(position);
            viewHolder.actionImg.setImageResource(bottomType.imgResId);
            viewHolder.actionName.setText(bottomType.stringResId);

            return convertView;
        }

        class ViewHolder {
            ImageView actionImg;
            TextView actionName;
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        gridView.setOnItemClickListener(listener);
    }

}
