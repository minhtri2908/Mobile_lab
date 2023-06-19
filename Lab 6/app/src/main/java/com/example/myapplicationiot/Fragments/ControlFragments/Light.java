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
import com.example.myapplicationiot.Fragments.API.BHmodel;
import com.example.myapplicationiot.Fragments.API.RetrofitClient;
import com.example.myapplicationiot.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Light extends Fragment {

    private TextView lightTextView;
    private Handler handler;
    private Runnable runnable;
    private static final long DELAY_MILLIS = 5000; // 5 seconds

    public Light() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lightTextView = view.findViewById(R.id.lightTextView);
        handler = new Handler(Looper.getMainLooper());

        //Retrofit builder

        ApiService methods = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        runnable = new Runnable() {
            @Override
            public void run() {
                Call<BHmodel> call = methods.getBH();

                call.enqueue(new Callback<BHmodel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(@NonNull Call<BHmodel> call, @NonNull Response<BHmodel> response) {
                        if (response.isSuccessful()) {
                            BHmodel lightResponse = response.body();
                            if (lightResponse != null) {
                                int light = lightResponse.getLight();
                                lightTextView.setText(Integer.toString(light));
                            }
                        } else {
                            // Handle null response body
                            Toast.makeText(getContext(), "Light data is empty", Toast.LENGTH_SHORT).show();
                        }

                        // Schedule the next execution after the delay
                        handler.postDelayed(runnable, DELAY_MILLIS);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BHmodel> call, @NonNull Throwable t) {
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