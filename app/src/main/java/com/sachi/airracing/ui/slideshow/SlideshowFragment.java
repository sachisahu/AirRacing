package com.sachi.airracing.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sachi.airracing.R;
import com.sachi.airracing.Util.Database;
import com.sachi.airracing.databinding.FragmentSlideshowBinding;
import com.sachi.airracing.ui.Adapters.RaceRowAdapter;

import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    Database db;
        ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow,null,false);

        db = new Database(getActivity());
        listView = view.findViewById(R.id.raceListView);
        db.featch();

        RaceRowAdapter adapter = new RaceRowAdapter(getActivity(),db.list);
        listView.setAdapter(adapter);

        return view;
    }


}