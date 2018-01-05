package com.example.jason.test.Home.News;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.test.GetSever.GetSeverActivity;
import com.example.jason.test.GetSever.ShowSeverListAdapter;
import com.example.jason.test.Main.Common;
import com.example.jason.test.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    //Log專用
    private final static String TAG = "NewsFragment";

    //Test
    private ArrayList<String> testArray = new ArrayList<>();

    //View
    private RecyclerView rvNewList;
    private SwipeRefreshLayout srNews;
    private Dialog dialog;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //Test
        for(int i = 0; i < 50; i++){
            if (i % 2 == 0) {
                testArray.add("Albee");
            } else {
                testArray.add("Jason");
            }
        }

        initView(view);
        initData();

        return view;
    }

    private void initData() {

        if (Common.networkConnected(getActivity())) {

            Log.d(TAG,"網路正常開始連線");

            dialog = ProgressDialog.show(getActivity(),
                    this.getString(R.string.dialog_loading_01), this.getString(R.string.dialog_loading_02), true);

            rvNewList.setAdapter(new NewsListAdapter(getActivity(), testArray));

            dialog.dismiss();
        }
        else {
            Log.d(TAG,"網路異常");
            NetWorkError();
        }

    }

    private void initView(View view) {

        rvNewList = (RecyclerView) view.findViewById(R.id.rvNewList);
        srNews = (SwipeRefreshLayout) view.findViewById(R.id.srNews);

        srNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srNews.setRefreshing(true);
//                GetSever();
                srNews.setRefreshing(false);
            }
        });

        rvNewList.setLayoutManager(new StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL));

        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,ItemTouchHelper.ACTION_STATE_IDLE){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    //所有Item交換
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(testArray, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(testArray, i, i - 1);
                    }
                }
                rvNewList.getAdapter().notifyItemMoved(fromPosition, toPosition);
                //返回true執行拖動
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                sportTypeList.remove(position);
//                rvSports.getAdapter().notifyItemRemoved(position);
            }
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(viewHolder.itemView,
                            "scaleX", 0.95f);
                    ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(viewHolder.itemView,
                            "scaleY", 0.95f);
                    scaleDownX.setDuration(100);
                    scaleDownY.setDuration(100);
                    AnimatorSet scaleDown = new AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                }

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(
                        viewHolder.itemView, "scaleX", 1f);
                ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(
                        viewHolder.itemView, "scaleY", 1f);
                scaleUpX.setDuration(100);
                scaleUpY.setDuration(100);

                AnimatorSet scaleUp = new AnimatorSet();
                scaleUp.play(scaleUpX).with(scaleUpY);
                scaleUp.start();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(rvNewList);
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
