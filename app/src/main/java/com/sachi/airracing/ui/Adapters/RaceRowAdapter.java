package com.sachi.airracing.ui.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sachi.airracing.DataClass.RaceDataClass;
import com.sachi.airracing.R;

import java.util.ArrayList;
import java.util.List;

public class RaceRowAdapter extends ArrayAdapter {

    List<RaceDataClass> raceList = new ArrayList<>();
    Activity activity;

    public RaceRowAdapter(@NonNull Activity activity,List<RaceDataClass> raceList) {
        super(activity, R.layout.race_row,raceList);
        this.raceList = raceList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.race_row,null,true);

        TextView txtRaceName = view.findViewById(R.id.RaceNameList);
        TextView txtDataTime = view.findViewById(R.id.RaceDateTimeList);

        RaceDataClass raceDataClass = (RaceDataClass) getItem(position);

        txtRaceName.setText(raceDataClass.RaceName);
        txtDataTime.setText(raceDataClass.Date+" "+raceDataClass.Time);

        return view;
    }
}
