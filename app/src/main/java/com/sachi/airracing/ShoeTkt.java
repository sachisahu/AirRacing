package com.sachi.airracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sachi.airracing.Util.Database;
import com.sachi.airracing.databinding.ActivityShoeTktBinding;

import java.io.ByteArrayOutputStream;

public class ShoeTkt extends AppCompatActivity {

    ActivityShoeTktBinding binding;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoeTktBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);


        Intent intent = this.getIntent();
        if(intent!=null){
            //int totalTkt = Integer.parseInt(intent.getStringExtra("totalTkt"));
            //Log.d("totlal",intent.getStringExtra("totalTkt"));
            //int tktPrice = Integer.parseInt(intent.getStringExtra("tktPrice"));
            //Log.d("price",intent.getStringExtra("totalTktPreson"));
            String noofPerson = intent.getStringExtra("totalTktPreson");
            String tktType = intent.getStringExtra("tkttype");
            String tktPrice = intent.getStringExtra("tktPrice");
            binding.racedateTime.setText(tktType+" "+noofPerson);
            binding.raceName.setText(intent.getStringExtra("RaceName"));
            binding.totalAMt.setText("Total : " + Integer.parseInt(noofPerson) * Integer.parseInt(tktPrice)+"$");
        }

        binding.saveS.setOnClickListener(i->{
            View view = getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
            MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Tkt","");
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            db.insert(""+intent.getStringExtra("RaceName"),""+intent.getStringExtra("RaceName"),intent.getStringExtra("tkttype"));
        });
    }
}