package com.example.gifsearchapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GiphyDataService {

    public static final String GIF_URL = "https://api.giphy.com/v1/gifs/search";
    public static final String API_KEY = "ANHAZleuIEWvB4nl42CvVJq4xmAAZw4z";

    Context context;

    public GiphyDataService(Context context) {
        this.context = context;
    }

    // Interface to return value
    public interface GifBySearchValueResponse {
        void onError(String message);

        void onResponse(List<GiphyResultModel> giphyResultModels);
    }

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

    public void getGifBySearchValue(String q, GifBySearchValueResponse gifBySearchValueResponse) {
        List<GiphyResultModel> giphyResultModels = new ArrayList<>();

        String url = GIF_URL + "?api_key=" + API_KEY + "&q=" + q;

        // Get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {

                        // Get the data in the array
                        GiphyResultModel one_gif = new GiphyResultModel();
                        JSONObject first_gif_from_api = (JSONObject) data.get(i);
                        JSONObject images = first_gif_from_api.getJSONObject("images");
                        JSONObject original = images.getJSONObject("original");

                        one_gif.setHeight(original.getString("height"));
                        one_gif.setWidth(original.getString("width"));
                        one_gif.setUrl(original.getString("url"));

                        giphyResultModels.add(one_gif);

                    }

                    gifBySearchValueResponse.onResponse(giphyResultModels);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "You shithead!", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }
}
