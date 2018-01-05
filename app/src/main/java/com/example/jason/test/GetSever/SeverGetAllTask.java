package com.example.jason.test.GetSever;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jason on 2018/1/3.
 */

public class SeverGetAllTask extends AsyncTask<Object, Integer, List<ServerList>> {

    //OKHttp
    private OkHttpClient client = new OkHttpClient();

    //Log專用
    private final static String TAG = "SeverGetAllTask";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(client == null) {
            //設置Timeout
            client = new OkHttpClient.Builder().connectTimeout(1000, TimeUnit.MINUTES)
                    .readTimeout(1000, TimeUnit.MINUTES)
                    .writeTimeout(1000, TimeUnit.MINUTES)
                    .build();

        }
    }
    @Override
    protected List<ServerList> doInBackground(Object... objects) {

        String jsonIn;
        String url = objects[0].toString();
        try {
            jsonIn = getRemoteData(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<ServerList>>() {
        }.getType();

        return gson.fromJson(jsonIn, listType);
    }

    private String getRemoteData(String url) throws IOException {

        String jsonIn = "";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()) {
                jsonIn = response.body().string();
            }
            else {
                jsonIn = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "jsonIn: " + jsonIn);

        return jsonIn;
    }
}
