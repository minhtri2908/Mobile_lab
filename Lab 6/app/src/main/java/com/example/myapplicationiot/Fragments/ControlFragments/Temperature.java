package com.example.myapplicationiot.Fragments.ControlFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplicationiot.Fragments.API.ApiService;
import com.example.myapplicationiot.Fragments.API.DHTmodel;
import com.example.myapplicationiot.Fragments.API.RetrofitClient;
import com.example.myapplicationiot.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Temperature extends Fragment {

    private TextView temperatureTextView;
    private Handler handler;
    private Runnable runnable;
    private static final long DELAY_MILLIS = 5000; // 5 seconds


    public Temperature() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        temperatureTextView = view.findViewById(R.id.temperatureTextView);
        handler = new Handler(Looper.getMainLooper());

        ApiService methods = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        runnable = new Runnable() {
            @Override
            public void run() {
                Call<DHTmodel> call = methods.getDHT();

                call.enqueue(new Callback<DHTmodel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(@NonNull Call<DHTmodel> call, @NonNull Response<DHTmodel> response) {
                        if (response.isSuccessful()) {
                            DHTmodel temperatureResponse = response.body();
                            if (temperatureResponse != null) {
                                double temperature = temperatureResponse.getTemperature();
                                temperatureTextView.setText(Double.toString(temperature));
                            }
                        } else {
                            // Handle null response body
                            Toast.makeText(getContext(), "Temperature data is empty", Toast.LENGTH_SHORT).show();
                        }

                        // Schedule the next execution after the delay
                        handler.postDelayed(runnable, DELAY_MILLIS);
                    }

                    @Override
                    public void onFailure(@NonNull Call<DHTmodel> call, @NonNull Throwable t) {
                        // Handle network failure
                        Toast.makeText(getContext(), "Failed to connect to the server", Toast.LENGTH_SHORT).show();

                        // Schedule the next execution after the delay
                        handler.postDelayed(runnable, DELAY_MILLIS);
                    }
                });
            }
        };

        // Start the initial execution with 0 delay
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Remove the callback when the view is destroyed
        handler.removeCallbacks(runnable);
    }
}