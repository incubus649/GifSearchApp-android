package com.example.gifsearchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.bumptech.glide.util.Preconditions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_searchGif;
    EditText et_dataInput;
    GridView gv_gifResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign values to each control of layout
        btn_searchGif = findViewById(R.id.btn_searchGif);
        et_dataInput = findViewById(R.id.et_dataInput);
        gv_gifResult = findViewById(R.id.gv_gifResult);

        // Create instance of the GiphyDataService class
        final GiphyDataService giphyDataService = new GiphyDataService(MainActivity.this);

        btn_searchGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String request = et_dataInput.getText().toString();

                // Call the replaceAll() method to replace all spaces to "+" for correct search
                request = request.replaceAll("\\s", "+");

                giphyDataService.getGifBySearchValue(request, new GiphyDataService.GifBySearchValueResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "You shithead!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<GiphyResultModel> giphyResultModels) {

                        RequestBuilder<Drawable> gifItemRequest = Glide.with(MainActivity.this).asDrawable();

                        GiphyAdapter adapter = new GiphyAdapter(MainActivity.this, giphyResultModels);
                        gv_gifResult.setAdapter(adapter);

                    }
                });

            }
        });
    }

    public class GiphyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<GiphyResultModel> giphyResultModels;

        public GiphyAdapter(Context context, ArrayList<GiphyResultModel> giphyResultModels) {
            super();
            this.context = context;
            this.giphyResultModels = giphyResultModels;
        }

        @Override
        public int getCount() {
            return giphyResultModels.size();
        }

        @Override
        public GiphyResultModel getItem(int i) {
            return giphyResultModels.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent)
        {

            View row = convertView;
            ViewHolder holder = null;

            if (row == null) {
                row = LayoutInflater.from(context).inflate(R.layout.grid_layout, null);
                holder = new ViewHolder();
                holder.imageView = row.findViewById(R.id.imageView);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }

            String gif = giphyResultModels.get(i).toString();
            Glide.with(context)
                    .load(gif)
                    .into(holder.imageView);

            return row;
        }

        public class ViewHolder {
            ImageView imageView;
        }
    }
}


