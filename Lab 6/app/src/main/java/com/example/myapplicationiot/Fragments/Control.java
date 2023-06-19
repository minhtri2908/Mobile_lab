package com.example.myapplicationiot.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.example.myapplicationiot.Adapters.ViewControlAdapter;
import com.example.myapplicationiot.MqttHandler;
import com.example.myapplicationiot.R;


public class Control extends Fragment {

    private TabLayout optionTabLayout;
    private ViewPager controlViewPager;
    ViewControlAdapter controlAdapter;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchLed1, switchLed2;

    private static final String MQTT_BROKER = "tcp://192.168.14.52:1883";
    private static final String MQTT_CLIENT_ID = "android_client";
    private MqttHandler mqttHandler;
    private static final String MQTT_LED1_TOPIC = "cttt/nhom7/led/n1";
    private static final String MQTT_LED2_TOPIC = "cttt/nhom7/led/n2";


    public Control() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        addTabs();

        mqttHandler = new MqttHandler();
        mqttHandler.connect(MQTT_BROKER, MQTT_CLIENT_ID);

        switchLed1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked ? "1" : "0";
                publishMessage(MQTT_LED1_TOPIC, message);
            }
        });

        switchLed2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked ? "1" : "0";
                publishMessage(MQTT_LED2_TOPIC, message);
            }
        });
    }

    private void init(View view){
        optionTabLayout = view.findViewById(R.id.optionLayout);
        controlViewPager = view.findViewById(R.id.controlViewpager);
        switchLed1 = view.findViewById(R.id.switchLed1);
        switchLed2 = view.findViewById(R.id.switchLed2);
    }

    private void addTabs(){
        optionTabLayout.addTab(optionTabLayout.newTab().setIcon(R.drawable.ic_temp));
        optionTabLayout.addTab(optionTabLayout.newTab().setIcon(R.drawable.ic_water));
        optionTabLayout.addTab(optionTabLayout.newTab().setIcon(R.drawable.ic_light));

        optionTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        optionTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        controlAdapter = new ViewControlAdapter(getChildFragmentManager(), optionTabLayout.getTabCount());
        controlViewPager.setAdapter(controlAdapter);

        controlViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(optionTabLayout));

        optionTabLayout.getTabAt(0).setIcon(R.drawable.ic_temp_fill);

        optionTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                controlViewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        optionTabLayout.getTabAt(0).setIcon(R.drawable.ic_temp_fill);
                        break;
                    case 1:
                        optionTabLayout.getTabAt(1).setIcon(R.drawable.ic_water_fill);
                        break;
                    case 2:
                        optionTabLayout.getTabAt(2).setIcon(R.drawable.ic_light_fill);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        optionTabLayout.getTabAt(0).setIcon(R.drawable.ic_temp);
                        break;
                    case 1:
                        optionTabLayout.getTabAt(1).setIcon(R.drawable.ic_water);
                        break;
                    case 2:
                        optionTabLayout.getTabAt(2).setIcon(R.drawable.ic_light);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:
                        optionTabLayout.getTabAt(0).setIcon(R.drawable.ic_temp_fill);
                        break;
                    case 1:
                        optionTabLayout.getTabAt(1).setIcon(R.drawable.ic_water_fill);
                        break;
                    case 2:
                        optionTabLayout.getTabAt(2).setIcon(R.drawable.ic_light_fill);
                        break;
                }

            }
        });

    }

    @Override
    public void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();
    }

    private void publishMessage(String topic, String message){
        mqttHandler.publish(topic, message);
    }

}