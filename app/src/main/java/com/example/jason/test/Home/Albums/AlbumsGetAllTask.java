package com.example.jason.test.Home.Albums;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.example.jason.test.Home.VO.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jason on 2018/1/8.
 */

public class AlbumsGetAllTask extends AsyncTask<Object, Integer, List<Image>> {

    //Log專用
    private final static String TAG = "AlbumsGetAllTask";

    private List<Image> images = new ArrayList<>();

    @Override
    protected List<Image> doInBackground(Object... objects) {

        String jsonIn;

        Context context = (Context) objects[1];
        try {
            images = getRemoteData(context);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

//        Gson gson = new Gson();
//        Type listType = new TypeToken<List<Image>>() {
//        }.getType();

        return images;
    }

    private List<Image> getRemoteData(Context context) throws IOException {

        try {

            ContentResolver cr = context.getContentResolver();
            String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
            //照片加入順序
            final String orderBy = MediaStore.Images.Media.DATE_ADDED;
            //查詢SD卡的圖片
            Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,null, null, orderBy);

            if(cursor != null && cursor.getCount() > 0) {

                int i = cursor.getCount() - 1;

                while (cursor.moveToNext()){

                    int imageid = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                    int imagepath = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                    images.add(new Image(cursor.getString(imageid),cursor.getString(imagepath)));
                    i-- ;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Collections.reverse(images);
        Log.d(TAG,"抓取圖片"+ images.size());

        return images;
    }
}
