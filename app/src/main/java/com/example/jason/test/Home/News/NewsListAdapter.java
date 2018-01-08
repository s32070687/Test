package com.example.jason.test.Home.News;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.test.Home.VO.TestData;
import com.example.jason.test.R;

import java.util.List;

/**
 * Created by Jason on 2018/1/5.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    //Log專用
    private final static String TAG = "NewsListAdapter";

    //View
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TestData> testDataList;

    public NewsListAdapter(Context context, List<TestData> testDataList) {
        Log.d(TAG,"開始設定資料1");
        this.context =context;
        this.testDataList = testDataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.newslist_item, parent, false);
        return new NewsListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, int position) {

        Log.d(TAG,"開始設定資料");
        holder.tvNewsTitle.setText(testDataList.get(position).getTitle());
        holder.tvNewsContent.setText(testDataList.get(position).getContent());
        holder.tvNewsAuthor.setText(testDataList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return testDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNewsTitle,tvNewsContent,tvNewsAuthor;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            tvNewsContent = (TextView) itemView.findViewById(R.id.tvNewsContent);
            tvNewsAuthor = (TextView) itemView.findViewById(R.id.tvNewsAuthor);
        }
    }
}
