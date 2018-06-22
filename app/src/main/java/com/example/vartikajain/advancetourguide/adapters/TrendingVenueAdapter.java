package com.example.vartikajain.advancetourguide.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikajain.advancetourguide.R;
import com.example.vartikajain.advancetourguide.models.FourSquareVenue;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 19-06-2018.
 */

public class TrendingVenueAdapter extends RecyclerView.Adapter<TrendingVenueAdapter.Viewholder> {
    Context context;
    ArrayList<FourSquareVenue> venues=new ArrayList<>();
    public TrendingVenueAdapter(Context context){
        this.context=context;
    }

    public void updateVenues(ArrayList<FourSquareVenue> venues){
        this.venues=venues;
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_trending_venue,parent,false);
        return new TrendingVenueAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        holder.tvVenueName.setText(venues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvVenueName;

            public Viewholder(View itemView) {
                super(itemView);
                tvVenueName= (TextView) itemView.findViewById(R.id.tvVenueName);
            }
        }
}
