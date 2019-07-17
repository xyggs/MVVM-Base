package com.example.myapplication.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by android on 2019/7/15
 */
public class TestAdapter extends RecyclerArrayAdapter<String> {
    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    class ViewHolder extends BaseViewHolder<String>{

        public ViewHolder(View view) {
            super((ViewGroup) view, R.layout.iten_xx);
        }
    }
}
