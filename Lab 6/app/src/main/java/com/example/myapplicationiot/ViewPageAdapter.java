package com.example.myapplicationiot;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplicationiot.Fragments.Regression;
import com.example.myapplicationiot.Fragments.Chart;
import com.example.myapplicationiot.Fragments.Control;
import com.example.myapplicationiot.Fragments.Home;
import com.example.myapplicationiot.Fragments.Logs;

public class ViewPageAdapter extends FragmentStatePagerAdapter{

    Home home = new Home();
    Control control = new Control();

    Regression regression = new Regression();
    Chart chart = new Chart();
    Logs logs = new Logs();
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return home;
            case 1:
                return control;
            case 2:
                return regression;
            case 3:
                return chart;
            case 4:
                return logs;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
