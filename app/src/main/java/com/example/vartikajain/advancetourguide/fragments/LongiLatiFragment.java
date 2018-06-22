package com.example.vartikajain.advancetourguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.BuildConfig;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.adapters.VenueAdapter;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareResponse;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LongiLatiFragment extends Fragment {


    public LongiLatiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_longi_lati, container, false);
        final ProgressBar pbNear= (ProgressBar) rootView.findViewById(R.id.pbNear);
        RecyclerView rvNear= (RecyclerView) rootView.findViewById(R.id.rvNear);
        TextView tvPlaces= (TextView) rootView.findViewById(R.id.tvPlaces);

        final VenueAdapter venueAdapter=new VenueAdapter(getActivity(),new ArrayList<FourSquareVenue>());
        rvNear.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvNear.setAdapter(venueAdapter);

        Bundle bundle=this.getArguments();
        String userll= bundle.getString("ll");
        String city=bundle.getString("city");
        tvPlaces.setText("Places near "+city);
        ApiService.getApi().getSearchVenues(BuildConfig.ClientID,BuildConfig.ClientSecret,userll).enqueue(new Callback<FourSquareJSON>() {
            @Override
            public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {

                pbNear.setIndeterminate(false);
                pbNear.setVisibility(View.GONE);
                FourSquareJSON fJson=response.body();
                FourSquareResponse fResponse=fJson.getResponse();
                ArrayList<FourSquareVenue> venues=fResponse.getVenues();
                venueAdapter.updateVenues(venues);
            }

            @Override
            public void onFailure(Call<FourSquareJSON> call, Throwable t) {

            }
        });
        return rootView;
    }

}
