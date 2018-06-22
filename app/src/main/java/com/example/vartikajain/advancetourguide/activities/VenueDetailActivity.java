package com.example.vartikajain.advancetourguide.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.BuildConfig;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.models.FourSquareEvent;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueDetailActivity extends AppCompatActivity {
    TextView tvName,tvCity,tvState,tvRating,tvTwitter,tvFacebook,tvInstagram,tvDesc,tvHours,tvVerified,tvCountry;
    Boolean verified;
    Button btnEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        tvName= (TextView) findViewById(R.id.tvName);
        tvCity= (TextView) findViewById(R.id.tvCity);
        tvState= (TextView) findViewById(R.id.tvState);
        tvRating= (TextView) findViewById(R.id.tvRating);
        tvTwitter= (TextView) findViewById(R.id.tvTwitter);
        tvFacebook= (TextView) findViewById(R.id.tvFacebook);
        tvInstagram= (TextView) findViewById(R.id.tvInstagram);
        tvCountry= (TextView) findViewById(R.id.tvCountry);
        btnEvents= (Button) findViewById(R.id.btnEvents);
//        tvDesc= (TextView) findViewById(R.id.tvDesc);
//        tvHours= (TextView) findViewById(R.id.tvHours);
        tvVerified= (TextView) findViewById(R.id.tvVerified);
        tvName.setText(getIntent().getStringExtra("venue_name"));
        tvCity.setText(getIntent().getStringExtra("venue_city")+",");
        tvState.setText(getIntent().getStringExtra("venue_state")+",");
        tvCountry.setText(getIntent().getStringExtra("venue_country"));
//        tvHours.setText(getIntent().getStringExtra("hours"));
        Double rating=getIntent().getDoubleExtra("venue_rating",0.0);
        if (rating==0.0)
            tvRating.setText( "N/A");
        else tvRating.setText(String.valueOf(rating));
        tvTwitter.setText(getIntent().getStringExtra("twitter"));
        tvFacebook.setText(getIntent().getStringExtra("facebook"));
        tvInstagram.setText(getIntent().getStringExtra("instagram"));
        verified=getIntent().getBooleanExtra("verified",false);
        if (verified==true)
            tvVerified.setText("Yes");
        else tvVerified.setText("No");

        final String id=getIntent().getStringExtra("venue_id");
//        btnEvents.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ApiService.getApi().getVenueEvents(id,BuildConfig.ClientID,BuildConfig.ClientSecret).enqueue(new Callback<ArrayList<FourSquareEvent>>() {
//                    @Override
//                    public void onResponse(Call<ArrayList<FourSquareEvent>> call, Response<ArrayList<FourSquareEvent>> response) {
//                        if (response.body()!=null)
//                        Log.d("TAG","events size:"+response.body().size());
//                        else Log.d("ERROR","error_code:"+response.code());
//                    }
//
//                    @Override
//                    public void onFailure(Call<ArrayList<FourSquareEvent>> call, Throwable t) {
//                        Toast.makeText(VenueDetailActivity.this,"Error in fetching events",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//        });

    }
}
