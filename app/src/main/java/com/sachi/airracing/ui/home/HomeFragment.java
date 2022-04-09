package com.sachi.airracing.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sachi.airracing.DataClass.RaceDataClass;
import com.sachi.airracing.FlashScreen;
import com.sachi.airracing.MainActivity;
import com.sachi.airracing.R;
import com.sachi.airracing.TktBooking;
import com.sachi.airracing.databinding.FragmentHomeBinding;
import com.sachi.airracing.ui.Adapters.RaceRowAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    List<RaceDataClass> racelist = new ArrayList<>();
    ListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //racelist.add(new RaceDataClass("Sachi","dasas","dfds"));


        loadData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home,null,false);

        listView = view.findViewById(R.id.raceListView);

        RaceRowAdapter adapter = new RaceRowAdapter(getActivity(),racelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TktBooking.class);
                intent.putExtra("RaceName",racelist.get(i).RaceName);
                intent.putExtra("RaceDatetime",racelist.get(i).Date +" "+racelist.get(i).Time);
                startActivity(intent);
            }
        });




        return view;
    }

    //Load data To List from Api
    private void loadData() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://ergast.com/api/f1/current.json")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("api CALL STAtus", "Failed :"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("API CALL STAtus", "Success");

                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String MRData = jsonObject.getString("MRData");
                    JSONObject jsonObjectMRData = new JSONObject(MRData);
                    String RaceTable = jsonObjectMRData.getString("RaceTable");
                    JSONObject jsonObjectRaces = new JSONObject(RaceTable);
                    String races = jsonObjectRaces.getString("Races");

                    JSONArray jsonArrayrace = new JSONArray(races);
                    for(int i=0;i<jsonArrayrace.length();i++){
                        JSONObject jsonObjectraceData = jsonArrayrace.getJSONObject(i);
                        String raceName = jsonObjectraceData.getString("raceName");
                        String raceDate = jsonObjectraceData.getString("date");
                        String raceTime = jsonObjectraceData.getString("time");
                        Log.d("Data Json",raceName + raceDate + raceTime);
                        racelist.add(new RaceDataClass(raceName+"",raceDate+"",raceTime+""));
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RaceRowAdapter adapter = new RaceRowAdapter(getActivity(),racelist);
                            listView.setAdapter(adapter);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}