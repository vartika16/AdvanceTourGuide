package com.example.vartikajain.advancetourguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.BuildConfig;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.adapters.ItemAdapter;
import com.example.vartikajain.advancetourguide.adapters.VenueAdapter;
import com.example.vartikajain.advancetourguide.models.FourSquareItem;
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
public class RecommendFragment extends Fragment {


    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_recommend, container, false);
        final ProgressBar pbRecommend= (ProgressBar) rootView.findViewById(R.id.pbRecommend);
        RecyclerView rvRecommend= (RecyclerView) rootView.findViewById(R.id.rvRecommend);
        final ItemAdapter itemAdapter=new ItemAdapter(getActivity(),new ArrayList<FourSquareItem>());

        rvRecommend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvRecommend.setAdapter(itemAdapter);
        Bundle bundle=this.getArguments();
        String userll= bundle.getString("ll");
        ApiService.getApi().getRecommendedVenues(BuildConfig.ClientID,BuildConfig.ClientSecret,userll).enqueue(new Callback<FourSquareJSON>() {
            @Override
            public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {

                pbRecommend.setIndeterminate(false);
                pbRecommend.setVisibility(View.GONE);
                FourSquareJSON fJson=response.body();
                ArrayList<FourSquareItem>items=fJson.getResponse().getGroups().get(0).getItems();
                itemAdapter.updateItems(items);
            }

            @Override
            public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                Toast.makeText(getActivity(),"Error in fetching data",Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

}
