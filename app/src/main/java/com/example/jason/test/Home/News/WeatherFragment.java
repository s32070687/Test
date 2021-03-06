package com.example.jason.test.Home.News;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.test.Home.VO.Weather;
import com.example.jason.test.Main.Common;
import com.example.jason.test.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    //Log專用
    private final static String TAG = "WeatherFragment";

    //View
    private RecyclerView rvWeatherList;
//    private SwipeRefreshLayout srNews;
    private Dialog dialog;
    private List<Weather> weatherList = null;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        initView(view);
        initData();

        Log.d(TAG,"更新");

        return view;
    }

    private void initView(View view) {

        rvWeatherList = (RecyclerView) view.findViewById(R.id.rvWeatherList);
//        srNews = (SwipeRefreshLayout) view.findViewById(R.id.srNews);
//
//        srNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                srNews.setRefreshing(true);
//                initData();
//                srNews.setRefreshing(false);
//            }
//        });

        rvWeatherList.setLayoutManager(new StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initData() {

        if (Common.networkConnected(getActivity())) {

            Log.d(TAG,"網路正常開始連線");

            dialog = ProgressDialog.show(getActivity(),"",
                     this.getString(R.string.dialog_loading_02), true);

            try {
                weatherList = new WeatherGetAllTask().execute(Common.WEATHER_URL).get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d(TAG,"平台列表" + weatherList.toString());

            if (weatherList == null || weatherList.isEmpty()) {
                dialog.dismiss();
                Log.d(TAG,"數據是空的");
                NetWorkError();
            }
            else {
                dialog.dismiss();
                Log.d(TAG,"連線成功有拿到縣市列表");
                rvWeatherList.setAdapter(new WeatherListAdapter(getActivity(), weatherList));
            }

        }
        else {
            Log.d(TAG,"網路異常");
            NetWorkError();
        }

    }

    //網路有問題
    public void NetWorkError() {
        new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher_error)
                .setTitle(R.string.dialog_message_01)
                .setMessage(R.string.dialog_message_02)
                .setPositiveButton(R.string.dialog_message_03, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"重新連線");
                        dialog.dismiss();
                        initData();
                    }
                })
                .setNegativeButton(R.string.dialog_message_04, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"取消");
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
