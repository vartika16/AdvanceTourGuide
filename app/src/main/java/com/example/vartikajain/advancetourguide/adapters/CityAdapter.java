package com.example.vartikajain.advancetourguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vartikajain.advancetourguide.MainActivity;
import com.example.vartikajain.advancetourguide.R;

import java.util.ArrayList;

/**
 * Created by VARTIKA JAIN on 20-06-2018.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.Viewholder> {
    Context context;
    ArrayList<String> cities;

    public CityAdapter(Context context,ArrayList<String> cities){
        this.context=context;
        this.cities=cities;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.list_item_city,parent,false);
        return new CityAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.tvCity.setText(cities.get(position));

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tvCity;

        public Viewholder(View itemView) {
            super(itemView);
            tvCity= (TextView) itemView.findViewById(R.id.tvCity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    String city=cities.get(pos);
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.putExtra("city_name", city);
                    context.startActivity(intent);

                }
            });
        }
    }
}
