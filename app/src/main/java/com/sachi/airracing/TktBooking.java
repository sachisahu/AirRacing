package com.sachi.airracing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sachi.airracing.DataClass.SeatTypeDataClass;
import com.sachi.airracing.databinding.ActivityTktBookingBinding;

import java.util.ArrayList;
import java.util.List;

public class TktBooking extends AppCompatActivity {

    ActivityTktBookingBinding binding;
    List<SeatTypeDataClass> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTktBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent = this.getIntent();
        String racename = null;
        String DateTime;

        Intent intent1 = new Intent(TktBooking.this,ShoeTkt.class);

        if(intent!=null){
            racename = intent.getStringExtra("RaceName");
            DateTime = intent.getStringExtra("RaceDatetime");

            binding.raceName.setText(racename);
            binding.racedateTime.setText(DateTime);
        }

        list.add(new SeatTypeDataClass(1,"Standing: 20$",20));
        list.add(new SeatTypeDataClass(2,"Normal: 50$",50));
        list.add(new SeatTypeDataClass(3,"First class: 100$",100));
        list.add(new SeatTypeDataClass(4,"VIP: 150$",150));

        ArrayAdapter<SeatTypeDataClass> seatArrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        binding.spinnerSeatType.setAdapter(seatArrayAdapter);

        binding.spinnerSeatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               intent1.putExtra("tktPrice",list.get(i).price+"");
                Toast.makeText(TktBooking.this, ""+list.get(i).price, Toast.LENGTH_SHORT).show();
               intent1.putExtra("tkttype",list.get(i).tktType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //int totalamt = amount[0]*Integer.parseInt(binding.noOfSeats.getText().toString());
        String finalRacename = racename;

            binding.btnReserve.setOnClickListener(i->{

                if(binding.noOfSeats.getText().toString().length()>=1) {
                    intent1.putExtra("RaceName", finalRacename);
                    intent1.putExtra("totalTktPreson", binding.noOfSeats.getText().toString());
            /*intent1.putExtra("tktType",seatTypeSelected[0]);
            intent1.putExtra("TotalAmt",totalamt+"");*/
                    startActivity(intent1);

                    //intent1.putExtra("TktTypeAndAmt",)
                }
                else {
                    Toast.makeText(this, "enter how much tkt", Toast.LENGTH_SHORT).show();
                }
            });






    }
}