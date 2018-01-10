package com.example.jason.test.Home.News;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.test.Home.VO.TestData;
import com.example.jason.test.Main.Common;
import com.example.jason.test.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    //Log專用
    private final static String TAG = "NewsFragment";

    //View
    private RecyclerView rvNewList;
    private SwipeRefreshLayout srNews;
    private Dialog dialog;
    private List<TestData> testDataList = null;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        initView(view);
        initData();

        Log.d(TAG,"更新");

        return view;
    }

    private void initView(View view) {

        rvNewList = (RecyclerView) view.findViewById(R.id.rvNewList);
        srNews = (SwipeRefreshLayout) view.findViewById(R.id.srNews);

        srNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srNews.setRefreshing(true);
                initData();
                srNews.setRefreshing(false);
            }
        });

        rvNewList.setLayoutManager(new StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initData() {

        if (Common.networkConnected(getActivity())) {

            Log.d(TAG,"網路正常開始連線");

            dialog = ProgressDialog.show(getActivity(),"",
                     this.getString(R.string.dialog_loading_02), true);

            try {
                testDataList = new NewsGetAllTask().execute("TestTask").get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d(TAG,"平台列表" + testDataList.toString());

            if (testDataList == null || testDataList.isEmpty()) {
                dialog.dismiss();
                Log.d(TAG,"數據是空的");
                NetWorkError();
            }
            else {
                dialog.dismiss();
                Log.d(TAG,"連線成功有拿到平台列表");
                rvNewList.setAdapter(new NewsListAdapter(getActivity(), testDataList));
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
