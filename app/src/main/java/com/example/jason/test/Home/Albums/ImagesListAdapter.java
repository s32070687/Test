package com.example.jason.test.Home.Albums;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jason.test.Home.VO.Image;
import com.example.jason.test.Main.Common;
import com.example.jason.test.R;

import java.util.List;

/**
 * Created by Jason on 2018/1/8.
 */

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ViewHolder> {

    //Log專用
    private final static String TAG = "WeatherListAdapter";

    //View
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Image> imageList;

    public ImagesListAdapter(Context context, List<Image> imageList) {
        Log.d(TAG,"開始設定資料1");
        this.context =context;
        this.imageList = imageList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ImagesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.imageslist_item, parent, false);
        return new ImagesListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImagesListAdapter.ViewHolder holder, int position) {

//        holder.rlAlbums.setLayoutParams(new GridView.LayoutParams(Common.phoneWidth/3,Common.phoneWidth/3));
        Uri uri = Uri.parse("file://" + imageList.get(position).getPath());

        Glide.with(context).load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivAlbums);

        holder.ivAlbums.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAlbums;
        RelativeLayout rlAlbums;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAlbums = (ImageView) itemView.findViewById(R.id.ivAlbums);
            rlAlbums = (RelativeLayout) itemView.findViewById(R.id.rlAlbums);
        }
    }
}
