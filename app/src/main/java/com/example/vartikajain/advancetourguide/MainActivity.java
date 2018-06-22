package com.example.vartikajain.advancetourguide;

import android.*;
import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.activities.DetectLocationActivity;
import com.example.vartikajain.advancetourguide.activities.HomeActivity;
import com.example.vartikajain.advancetourguide.fragments.LongiLatiFragment;
import com.example.vartikajain.advancetourguide.fragments.NearFragment;
import com.example.vartikajain.advancetourguide.fragments.RecommendFragment;
import com.example.vartikajain.advancetourguide.models.FourSquareItem;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareResponse;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    FragmentManager fragmentManager;
    RecommendFragment recommendFragment;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    TextView tvLocation,tvChange,tvCurrentLoc;
    String userLL;
    String near;
    ArrayList<FourSquareVenue> venues;
    int value;
    String city_name;
    ImageView ivBack;
    LongiLatiFragment longilatifragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLocation= (TextView) findViewById(R.id.tvLocation);
        tvChange= (TextView) findViewById(R.id.tvChange);
        tvCurrentLoc= (TextView) findViewById(R.id.tvCurrentLoc);
        ivBack= (ImageView) findViewById(R.id.ivBack);
        fragmentManager=getSupportFragmentManager();
        venues=new ArrayList<>();
        city_name=getIntent().getStringExtra("city_name");
        Log.d("TAG","city_name is:"+city_name);

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    111);
        }
        else {
            if (checkPlayServices()){
                buildGoogleApiClient();
            }
        }

        tvCurrentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=0;
                city_name="current";
                displayLocation();
            }
        });
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DetectLocationActivity.class);
                intent.putExtra("VenueCity",tvLocation.getText());
                startActivity(intent);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient!=null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient!=null)
            mGoogleApiClient.disconnect();
        super.onStop();

    }

    public void displayLocation(){
        if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            return;
        mLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (city_name=="current"||city_name==null)
        {

        if (mLocation!=null) {
            userLL= mLocation.getLatitude()+","+ mLocation.getLongitude();
            Log.d("TAG","location is"+userLL);
            ApiService.getApi().getSearchVenues(BuildConfig.ClientID,BuildConfig.ClientSecret,userLL).enqueue(new Callback<FourSquareJSON>() {
                @Override
                public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {
                    FourSquareJSON fJson=response.body();
                    FourSquareResponse fResponse=fJson.getResponse();
                    ArrayList<FourSquareVenue> venues=fResponse.getVenues();

                    Log.d("TAG","Venues size is:"+venues.size());
                    FourSquareVenue venue=venues.get(0);
                    tvLocation.setText(venue.getLocation().getCity());
                    beginLLFrame();
                    beginLLRecommend();
                }


                @Override
                public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Error in fetching data",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
            Toast.makeText(this, "Couldn't get the location. Make sure location is turned on device", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            tvLocation.setText(city_name);
            ApiService.getApi().getSearchVenuesByArea(BuildConfig.ClientID,BuildConfig.ClientSecret,city_name).enqueue(new Callback<FourSquareJSON>() {
                @Override
                public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {

                    FourSquareJSON fJson=response.body();
                    FourSquareResponse fResponse=fJson.getResponse();
                    ArrayList<FourSquareVenue> venues=fResponse.getVenues();
                    Log.d("TAG","Venues size is:"+venues.size());
                    FourSquareVenue venue=venues.get(0);
//                    beginNearFrame();
                    userLL=venue.getLocation().getLat()+","+venue.getLocation().getLng();
                    beginLLFrame();
                    beginLLRecommend();

                }

                @Override
                public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Error in fetching data",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    private void beginLLFrame() {
        Bundle bundle=new Bundle();
        bundle.putString("ll",userLL);
        bundle.putString("city",tvLocation.getText().toString());
        longilatifragment=new LongiLatiFragment();
        longilatifragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flFragContainer,longilatifragment).commit();
    }

    private void beginLLRecommend() {
        Bundle bundle=new Bundle();
        bundle.putString("ll",userLL);
        recommendFragment=new RecommendFragment();
        recommendFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flRecommendContainer,recommendFragment).commit();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    private boolean checkPlayServices() {
        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode!=ConnectionResult.SUCCESS){
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode,this,1712).show();
            }
            else {
                Toast.makeText(this,"This device isn't supported",Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==111){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                if (checkPlayServices())
                    buildGoogleApiClient();
            }}
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        value=getIntent().getIntExtra("calling_activity",0);
        if (value==101){
            near=getIntent().getStringExtra("near");
            Log.d("TAG","venue name is:"+near);
            tvLocation.setText(near);
            ApiService.getApi().getSearchVenuesByArea(BuildConfig.ClientID,BuildConfig.ClientSecret,near).enqueue(new Callback<FourSquareJSON>() {
                @Override
                public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {

                    FourSquareJSON fJson=response.body();
                    FourSquareResponse fResponse=fJson.getResponse();
                    ArrayList<FourSquareVenue> venues=fResponse.getVenues();
                    Log.d("TAG","Venues size is:"+venues.size());
                    FourSquareVenue venue=venues.get(0);
//                    beginNearFrame();
                    userLL=venue.getLocation().getLat()+","+venue.getLocation().getLng();
                    beginLLFrame();
                    beginLLRecommend();
                }

                @Override
                public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Error in fetching data",Toast.LENGTH_SHORT).show();
                }
            });
//            value=0;
        }
        else if(value==0)
            displayLocation();
    }

//    private void beginNearFrame() {
//        Bundle bundle=new Bundle();
//        bundle.putString("near",near);
//        bundle.putString("city",tvLocation.getText().toString());
//        nearFragment=new NearFragment();
//        nearFragment.setArguments(bundle);
//        fragmentManager.beginTransaction().replace(R.id.flFragContainer,nearFragment).commit();
//    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MainActivity.this,"Can't connect with google api services",Toast.LENGTH_SHORT).show();

    }
}
