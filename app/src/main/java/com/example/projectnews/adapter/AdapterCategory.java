package com.example.projectnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectnews.R;
import com.example.projectnews.model.Category;

import java.util.List;

public class AdapterCategory extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<Category> categoryList;
    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public AdapterCategory(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(layout,null);
        ImageView img = (ImageView) view.findViewById(R.id.imgchuyemuc);

        TextView txt =(TextView) view.findViewById(R.id.textviewTenbaochuyenmuc);

        Category cm = categoryList.get(i);
        img.setImageResource(cm.getHinhanhchuyenmucl());
        txt.setText(cm.getTenchuyenmuc());
        img.setImageResource(cm.getHinhanhchuyenmucl());


        return  view;
    }
}
