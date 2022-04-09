package com.sachi.airracing.ui;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.sachi.airracing.R;
import com.sachi.airracing.Util.Sp;

import okhttp3.internal.Util;

public class LoginFragment extends Fragment {

    Sp sp = new Sp();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        VideoView videoView = view.findViewById(R.id.video);
        EditText phone = view.findViewById(R.id.edtPhone);
        EditText pass = view.findViewById(R.id.edtPass);
         Button loginBtn = view.findViewById(R.id.LoginBtn);
        getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);

        Uri uri = Uri.parse("android.resource://com.sachi.airracing/"+R.raw.video);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        loginBtn.setOnClickListener(i->{
            if(phone.getText().toString().length()>9 && phone.getText().toString().length()<12 && pass.getText().toString().length()>4){
                Sp.SaveShared(getActivity(),"login","login","Logout");
            }
            else {
                Toast.makeText(getActivity(), "Enter Correct Phone And password should be more than 4 dgts", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}