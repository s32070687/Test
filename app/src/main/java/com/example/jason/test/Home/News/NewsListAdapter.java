package com.example.jason.test.Home.News;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.test.R;

import java.util.ArrayList;

/**
 * Created by Jason on 2018/1/5.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    //Log專用
    private final static String TAG = "NewsListAdapter";

    //View
    private Context context;
    private LayoutInflater layoutInflater;

    //Test
    private ArrayList<String> testArray;

    public NewsListAdapter(Context context, ArrayList<String> testArray) {
        this.context =context;
        this.testArray = testArray;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.newslist_item, parent, false);
        return new NewsListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, int position) {
        holder.tvNewsTitle.setText(testArray.get(position));
    }

    @Override
    public int getItemCount() {
        return testArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNewsTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
        }
    }
}
