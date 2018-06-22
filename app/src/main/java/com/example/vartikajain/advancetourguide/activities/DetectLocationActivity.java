package com.example.vartikajain.advancetourguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.BuildConfig;
import com.example.vartikajain.advancetourguide.MainActivity;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.adapters.TrendingVenueAdapter;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareResponse;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetectLocationActivity extends AppCompatActivity {
    ImageView ivSearch;
    EditText etSearch;
    RecyclerView rvTrending;
    TextView tvTrending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_location);
        ivSearch= (ImageView) findViewById(R.id.ivSearch);
        etSearch= (EditText) findViewById(R.id.etSearch);
        rvTrending= (RecyclerView) findViewById(R.id.rvTrending);
        tvTrending= (TextView) findViewById(R.id.tvTrending);

        final TrendingVenueAdapter trendingVenueAdapter=new TrendingVenueAdapter(this);
        rvTrending.setLayoutManager(new LinearLayoutManager(this));
        rvTrending.setAdapter(trendingVenueAdapter);

        final String city=getIntent().getStringExtra("VenueCity");
        tvTrending.setText("Trending Locations in "+city);

        ApiService.getApi().getTrendingVenues(BuildConfig.ClientID,BuildConfig.ClientSecret,city)
                .enqueue(new Callback<FourSquareJSON>() {
            @Override
            public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {
                FourSquareJSON fsj=response.body();
                FourSquareResponse fsr=fsj.getResponse();
                ArrayList<FourSquareVenue> venues=fsr.getVenues();
                Log.d("TAG","venues size is:"+venues.size());
                if (venues.size()==0)
                    Toast.makeText(DetectLocationActivity.this,"No Trending locations in "+city,Toast.LENGTH_SHORT).show();
                else
                trendingVenueAdapter.updateVenues(venues);
            }

            @Override
            public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                Toast.makeText(DetectLocationActivity.this,"Error in fetching data",Toast.LENGTH_SHORT).show();

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DetectLocationActivity.this, MainActivity.class);
                String near=etSearch.getText().toString();
                i.putExtra("near",near);
                i.putExtra("calling_activity",101);
                startActivity(i);
            }
        });
    }


}
