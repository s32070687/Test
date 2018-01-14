package com.example.jason.test.Home.News;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.test.Home.VO.TestData;
import com.example.jason.test.Home.VO.Weather;
import com.example.jason.test.R;

import java.util.List;

/**
 * Created by Jason on 2018/1/5.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    //Log專用
    private final static String TAG = "WeatherListAdapter";

    //View
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Weather> weatherList;

    public WeatherListAdapter(Context context, List<Weather> weatherList) {
        Log.d(TAG,"開始設定資料1");
        this.context =context;
        this.weatherList = weatherList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.weatherlist_item, parent, false);
        return new WeatherListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.ViewHolder holder, int position) {

        Log.d(TAG,"開始設定資料");
        holder.tvCity.setText(weatherList.get(position).getName());
//        holder.tvTowns.setText(weatherList.get(position).getTowns().get(position).getTownsname());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCity,tvTowns;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvTowns = (TextView) itemView.findViewById(R.id.tvTowns);
        }
    }
}
