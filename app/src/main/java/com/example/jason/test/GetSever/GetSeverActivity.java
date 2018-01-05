package com.example.jason.test.GetSever;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.jason.test.Main.Common;
import com.example.jason.test.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GetSeverActivity extends AppCompatActivity {

    //Log專用
    private final static String TAG = "LoadingActivity";

    //View元件
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvSeverList;
    private String SEVER_URL = "";
    private Dialog dialog;

    //Test
    private ArrayList<String> testArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_sever);

        //Test
        for(int i = 0; i < 50; i++){
            if (i % 2 == 0) {
                testArray.add("Albee");
            } else {
                testArray.add("Jason");
            }
        }

        //初始化資料
        init();
    }

    private void init() {
        rvSeverList = (RecyclerView) findViewById(R.id.rvSeverList);
        rvSeverList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                GetSever();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,ItemTouchHelper.ACTION_STATE_IDLE){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                if (fromPosition < toPosition) {

                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(testArray, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(testArray, i, i - 1);
                    }
                }
                rvSeverList.getAdapter().notifyItemMoved(fromPosition, toPosition);
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                testArray.remove(position);
//                rvSeverList.getAdapter().notifyItemRemoved(position);
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
        itemTouchHelper.attachToRecyclerView(rvSeverList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetSever();
    }

    private void GetSever() {
        if (Common.networkConnected(this)) {

            Log.d(TAG,"網路正常開始連線");

            dialog = ProgressDialog.show(GetSeverActivity.this,
                    this.getString(R.string.dialog_loading_01), this.getString(R.string.dialog_loading_02), true);

            rvSeverList.setAdapter(new ShowSeverListAdapter(GetSeverActivity.this, testArray));

            dialog.dismiss();
//            List<ServerList> serverLists = null;
//
//            try {
//                serverLists = new SeverGetAllTask().execute(SEVER_URL).get();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                Log.e(TAG, e.toString());
//            }
//
//            Log.d(TAG,"平台列表" + serverLists.toString());
//
//            if (serverLists == null || serverLists.isEmpty()) {
//                Log.d(TAG,"連線失敗 平台是空的");
//                dialog.dismiss();
//                NetWorkError();
//            }
//            else {
//                Log.d(TAG,"連線成功有拿到平台列表");
//                dialog.dismiss();
//                rvSeverList.setAdapter(new ShowSeverListAdapter(GetSeverActivity.this, testArray));
//            }
        }
        else {
            Log.d(TAG,"網路異常");
            NetWorkError();
        }
    }

    //網路有問題
    public void NetWorkError() {
        new AlertDialog.Builder(GetSeverActivity.this)
                .setIcon(R.mipmap.ic_launcher_error)
                .setTitle(R.string.dialog_message_01)
                .setMessage(R.string.dialog_message_02)
                .setPositiveButton(R.string.dialog_message_03, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"重新連線");
                        dialog.dismiss();
                        GetSever();
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
