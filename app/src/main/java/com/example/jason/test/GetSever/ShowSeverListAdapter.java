package com.example.jason.test.GetSever;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2018/1/3.
 */

public class ShowSeverListAdapter extends RecyclerView.Adapter<ShowSeverListAdapter.ViewHolder> {
    //Log專用
    private final static String TAG = "ShowSeverListAdapter";

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ServerList> serverLists;

    //Test
    private ArrayList<String> testArray;

    public ShowSeverListAdapter(Context context, ArrayList<String> testArray) {
        this.context = context;
        this.testArray = testArray;
//        this.serverLists = serverLists;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ShowSeverListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.severlist_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowSeverListAdapter.ViewHolder holder, int position) {

//        final ServerList serverList = serverLists.get(position);
//        Log.d(TAG,serverList.getName() + " Name");

//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Common.phoneHeight/8);
//        holder.cvItem.setLayoutParams(layoutParams);
        holder.tvSeverName.setText(testArray.get(position));
    }

    @Override
    public int getItemCount() {
        return testArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSeverName;
        CardView cvItem;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSeverName = (TextView) itemView.findViewById(R.id.tvSeverName);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
        }
    }
}
