package com.example.myapplicationiot.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.example.myapplicationiot.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Regression extends Fragment {

    private EditText editTextValue;
    private TextView textViewResult;

    public Regression() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regression, container, false);

        editTextValue = view.findViewById(R.id.editTextNumber);
        textViewResult = view.findViewById(R.id.textViewResult);
        Button buttonPost = view.findViewById(R.id.buttonCalculate);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editTextValue.getText().toString();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                        String requestBody = "{\"Humidity\": \"" + value + "\"}";
                        RequestBody body = RequestBody.create(requestBody, mediaType);
                        Request request = new Request.Builder()
                                .url("https://api-weather-tin.onrender.com/")
                                .post(body)
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                String responseBody = response.body().string();
                                JsonParser parser = new JsonParser();
                                JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();

                                if (jsonObject.has("prediction")) {
                                    int prediction = jsonObject.get("prediction").getAsInt();
                                    textViewResult.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            textViewResult.setText("Kết quả: " + prediction + "'c");
                                        }
                                    });
                                } else {
                                }
                            } else {
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

        return view;
    }
}