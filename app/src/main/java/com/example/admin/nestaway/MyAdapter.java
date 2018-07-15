package com.example.admin.nestaway;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {
    private ArrayList<Integer> images;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Integer> images)
    {
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();  // Count No of Images
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View v = inflater.inflate(R.layout.slide,container,false);
        ImageView myImage = v.findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        container.addView(v,0);
        return v;
    }
}
