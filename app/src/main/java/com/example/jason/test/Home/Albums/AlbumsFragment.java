package com.example.jason.test.Home.Albums;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.test.Home.VO.Image;
import com.example.jason.test.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    //Log專用
    private final static String TAG = "AlbumsFragment";

    //View
    private RecyclerView rvImageList;
    private Dialog dialog;

    //Data
    private List<Image> images = null;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        initView(view);
        getItemList();

        return view;
    }

    private void initView(View view) {

        rvImageList = (RecyclerView) view.findViewById(R.id.rvImageList);
        rvImageList.setLayoutManager(new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL));
    }

    public void getItemList() {
        allScan();

        dialog = ProgressDialog.show(getActivity(),"",
                this.getString(R.string.dialog_loading_02), true);

        try {
            images = new AlbumsGetAllTask().execute("AlbumsTask",getActivity()).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (images == null || images.isEmpty()) {
            dialog.dismiss();
            Log.d(TAG,"抓取圖片為空");
        }
        else {
            dialog.dismiss();
            Log.d(TAG,"有抓到圖片");
            rvImageList.setAdapter(new ImagesListAdapter(getActivity(), images));
        }

    }

    public void allScan() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent mediaScanIntent = new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(Environment.getExternalStorageDirectory()); // out is your output file
            mediaScanIntent.setData(contentUri);
            getActivity().sendBroadcast(mediaScanIntent);

        } else {
            getActivity().sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://"
                            + Environment.getExternalStorageDirectory())));
        }
    }

}
