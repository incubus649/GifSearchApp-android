package com.example.gifsearchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btn_searchGif;
    EditText et_dataInput;
    // ListView lv_gifResult;
    GridView gv_gifResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign values to each control of layout
        btn_searchGif = findViewById(R.id.btn_searchGif);
        et_dataInput = findViewById(R.id.et_dataInput);
        // lv_gifResult = findViewById(R.id.lv_gifResult);
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
                    public void onResponse(List<GiphyResultModel> giphyResultModels) {

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, giphyResultModels);
                        // ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this, giphyResultModels);

                        gv_gifResult.setAdapter(arrayAdapter);
                        // gv_gifResult.setAdapter(imageAdapter);
                    }
                });

            }
        });
    }


    /*

    TODO: addTextChangedListener not working for some reason thus making it impossible for auto-submit et_dataInput

    // Implemented editText that sends request 300 milliseconds after user stops typing
    // Source code - https://stackoverflow.com/questions/35224459/how-to-detect-if-users-stop-typing-in-edittext-android

    // Delay in milliseconds for auto-submitting editText after user stops typing
    long delay = 300;

    // Time in milliseconds when the last change was made
    long lastEdit = 0;

    Handler handler = new Handler();

    // Starts a thread after n milliseconds when the user stops typing
    // Sends request to Giphy API
    private Runnable inputFinished = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (lastEdit + delay)) {
                // API request here
            }
        }
    };

    et_dataInput.addTextChangedListener(new TextWatcher() {

        @Override
        public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after){

        }

        @Override
        public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            handler.removeCallbacks(inputFinished);
        }

        @Override
        public void afterTextChanged(final Editable s) {
            // Event is not triggered if the editText is empty
            if (s.length() > 0) {
                lastEdit = System.currentTimeMillis();
                handler.postDelayed(inputFinished, delay);
            }
        }
    });

     */
}



