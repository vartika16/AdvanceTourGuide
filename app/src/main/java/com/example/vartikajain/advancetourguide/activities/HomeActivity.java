package com.example.vartikajain.advancetourguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vartikajain.advancetourguide.MainActivity;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.adapters.CityAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvCities;
    TextView tvCurrentLoc;
    CityAdapter cityAdapter;
    ArrayList<String> cities=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvCurrentLoc= (TextView) findViewById(R.id.tvCurrentLoc);
        rvCities= (RecyclerView) findViewById(R.id.rvCities);
        generate(cities);
        cityAdapter=new CityAdapter(this,cities);
        rvCities.setLayoutManager(new GridLayoutManager(this,2));
        rvCities.setAdapter(cityAdapter);

        tvCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, MainActivity.class);
//                intent.putExtra("city_name","curr");
                startActivity(intent);
            }
        });
    }

    private void generate(ArrayList<String> cities) {
        cities.add("Amsterdam");
        cities.add("Bangkok");
        cities.add("Barcelona");
        cities.add("Berlin");
        cities.add("Dubai");
        cities.add("Dublin");
        cities.add("Hong Kong");
        cities.add("Istanbul");
        cities.add("Kuala Lumpur");
        cities.add("Las Vegas");
        cities.add("London");
        cities.add("Los Angeles");
        cities.add("Madrid");
        cities.add("Miami");
        cities.add("Milan");
        cities.add("Moscow");
        cities.add("New Delhi");
        cities.add("New York");
        cities.add("Paris");
        cities.add("Prague");
        cities.add("Rome");
        cities.add("Singapore");
        cities.add("Tokyo");
        cities.add("Vienna");
        cities.add("Toronto");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");
//        cities.add("New Delhi");

    }
}
