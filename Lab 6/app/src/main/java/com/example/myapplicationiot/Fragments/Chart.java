package com.example.myapplicationiot.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.myapplicationiot.R;

import java.util.ArrayList;
import java.util.List;


public class Chart extends Fragment {

    LineChart humidityLinechart;
    BarChart tempBarchart, lightBarchart;


    public Chart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lightBarchart = view.findViewById(R.id.lightlinechart);
        tempBarchart = view.findViewById(R.id.templinechart);
        humidityLinechart = view.findViewById(R.id.humibarchart);



        //temperature
        ArrayList<BarEntry> temperature = new ArrayList<>();
        temperature.add(new BarEntry(1, 24.3f));
        temperature.add(new BarEntry(2, 22.2f));
        temperature.add(new BarEntry(3, 24.8f));
        temperature.add(new BarEntry(4, 23.3f));
        temperature.add(new BarEntry(5, 20.5f));
        temperature.add(new BarEntry(6, 24.8f));
        temperature.add(new BarEntry(7, 25.0f));
        temperature.add(new BarEntry(8, 22.5f));
        temperature.add(new BarEntry(9, 20.8f));
        temperature.add(new BarEntry(10, 22.8f));

        BarDataSet dataSet = new BarDataSet(temperature, "Temperature");
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);
        dataSet.setColor(Color.RED);

        BarData barData = new BarData(dataSet);

        tempBarchart.setFitBars(true);
        tempBarchart.setData(barData);
        tempBarchart.getDescription().setText("Bar chart");
        tempBarchart.animateY(2000);

        //humidity
        List<Entry> humidity = new ArrayList<>();
        humidity.add(new Entry(1, 78));
        humidity.add(new Entry(2, 81));
        humidity.add(new Entry(3, 74));
        humidity.add(new Entry(4, 76));
        humidity.add(new Entry(5, 73));
        humidity.add(new Entry(6, 69));
        humidity.add(new Entry(7, 70));
        humidity.add(new Entry(8, 72));
        humidity.add(new Entry(9, 74));
        humidity.add(new Entry(10, 78));

        LineDataSet humiditydataSet = new LineDataSet(humidity, "humidity");
        humiditydataSet.setColor(Color.BLUE);
        humiditydataSet.setCircleColor(Color.BLACK);
        humiditydataSet.setFillDrawable(getResources().getDrawable(R.drawable.fillgraph));
        LineData humiditylineData = new LineData(humiditydataSet);

        humidityLinechart.setData(humiditylineData);

        Description humiditydescription = new Description();
        humiditydescription.setText("light Chart");
        humidityLinechart.setDescription(humiditydescription);

        humidityLinechart.invalidate();

        //Light
        ArrayList<BarEntry> light = new ArrayList<>();
        light.add(new BarEntry(1, 60));
        light.add(new BarEntry(2, 32));
        light.add(new BarEntry(3, 28));
        light.add(new BarEntry(4, 8));
        light.add(new BarEntry(5, 26));
        light.add(new BarEntry(6, 60));
        light.add(new BarEntry(7, 50));
        light.add(new BarEntry(8, 103));
        light.add(new BarEntry(9, 43));
        light.add(new BarEntry(10, 12));

        BarDataSet dataSetname = new BarDataSet(light, "Light");
        dataSetname.setValueTextColor(Color.BLACK);
        dataSetname.setValueTextSize(16f);
        dataSetname.setColor(Color.YELLOW);
        BarData lightbarData = new BarData(dataSetname);


        lightBarchart.setFitBars(true);
        lightBarchart.setData(lightbarData);
        lightBarchart.getDescription().setText("Bar chart");
        lightBarchart.animateY(2000);

    }
}