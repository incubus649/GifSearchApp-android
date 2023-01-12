package com.example.gifsearchapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    Context context;
    List<GiphyResultModel> items;

/*
    Drawable item;

    // Get images from url
    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "gif");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
*/
    ImageAdapter(Context context, List<GiphyResultModel> list)
    {
        this.context = context;
        items = list;
    }

    @Override public int getCount()
    {
        return 0;
    }

    @Override public Object getItem(int i)
    {
        return null;
    }

    @Override public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_layout, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView);

        // item = loadImageFromWebOperations(String.valueOf(items));
        // imageView.setImageDrawable(item);
        // imageView.setImageDrawable((Drawable) items);
        return view;
    }
}