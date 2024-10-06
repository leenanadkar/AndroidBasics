package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String vegetableList[];
    int vegetableImages[];
    LayoutInflater inflater;

    public CustomAdapter(Context ctx, String inputTextArray[], int inputImagesArray[])
    {
        this.context = ctx;
        this.vegetableList = inputTextArray;
        this.vegetableImages = inputImagesArray;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return vegetableList.length;
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

        convertView = inflater.inflate(R.layout.custom_list,null);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView);

        textView.setText(vegetableList[position]);
        imageView.setImageResource(vegetableImages[position]);

        return convertView;
    }
}
