package com.example.vartikajain.advancetourguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vartikajain.advancetourguide.Api.ApiService;
import com.example.vartikajain.advancetourguide.BuildConfig;
import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.activities.VenueDetailActivity;
import com.example.vartikajain.advancetourguide.models.FourSquareJSON;
import com.example.vartikajain.advancetourguide.models.FourSquareResponse;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by VARTIKA JAIN on 18-06-2018.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.Viewholder> {
    Context context;
    ArrayList<FourSquareVenue> venues;
    public VenueAdapter(Context context, ArrayList<FourSquareVenue> venues){
        this.context=context;
        this.venues=venues;
    }
    public void updateVenues(ArrayList<FourSquareVenue> venues){
        this.venues=venues;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_venue,parent,false);
        return new VenueAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.tvName.setText(venues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvName;

        public Viewholder(View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Log.d("TAG","Adapter position clicked:"+pos);
                    FourSquareVenue clickedVenue=venues.get(pos);

                    ApiService.getApi().getVenueDetails(clickedVenue.getId(), BuildConfig.ClientID,BuildConfig.ClientSecret).enqueue(new Callback<FourSquareJSON>() {
                        @Override
                        public void onResponse(Call<FourSquareJSON> call, Response<FourSquareJSON> response) {
                            if (response.body()!=null){
                            FourSquareVenue venue=response.body().getResponse().getVenue();
                            Intent intent=new Intent(context, VenueDetailActivity.class);
                            intent.putExtra("venue_id",venue.getId());
                            intent.putExtra("venue_name",venue.getName());
                            intent.putExtra("venue_city",venue.getLocation().getCity());
                            intent.putExtra("venue_state",venue.getLocation().getState());
                            intent.putExtra("venue_country",venue.getLocation().getCountry());
                            intent.putExtra("verified",venue.isVerified());
//                            if (venue.getHours().getStatus()==null)
//                                intent.putExtra("hours","N/A");
//                            else
//                                intent.putExtra("hours",venue.getHours().getStatus());

                            if (venue.getRating()==null)
                                intent.putExtra("venue_rating",0.0);
                            else intent.putExtra("venue_rating",venue.getRating());

                            if (venue.getContact().getTwitter()==null)
                                intent.putExtra("twitter","N/A");
                            else intent.putExtra("twitter",venue.getContact().getTwitter());

                            if (venue.getContact().getFacebookUsername()==null)
                                intent.putExtra("facebook","N/A");
                            else intent.putExtra("facebook",venue.getContact().getFacebookUsername());

                            if (venue.getContact().getInstagram()==null)
                                intent.putExtra("instagram","N/A");
                            else intent.putExtra("instagram",venue.getContact().getInstagram());
//                            Log.d("CATEGORY","size:"+venue.getCategories().size());

                            context.startActivity(intent);}
                            else Log.d("error","error_code"+response.code());
//


                        }

                        @Override
                        public void onFailure(Call<FourSquareJSON> call, Throwable t) {
                            Toast.makeText(context,"Error in fetching venue details",Toast.LENGTH_SHORT).show();
                            Log.d("ERROR",t.getMessage());

                        }
                    });
//


                }
            });
        }
    }
}
