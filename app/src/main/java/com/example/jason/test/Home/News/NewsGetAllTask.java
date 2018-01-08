package com.example.jason.test.Home.News;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jason.test.Home.VO.TestData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jason on 2018/1/5.
 */

public class NewsGetAllTask extends AsyncTask<Object, Integer, List<TestData>> {

    //
    //OKHttp
    private OkHttpClient client = new OkHttpClient();

    //Log專用
    private final static String TAG = "NewsGetAllTask";

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
    protected List<TestData> doInBackground(Object... objects) {
        String jsonIn;
        String url = objects[0].toString();
        try {
            jsonIn = getRemoteData(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<TestData>>() {
        }.getType();

        return gson.fromJson(jsonIn, listType);
    }

    private String getRemoteData(String url) throws IOException {

        String jsonIn = "";

        Log.d(TAG,"任務名稱" + url);

        if(url.equals("TestTask")) {

            JSONArray JArray= new JSONArray();

            JSONObject jsonObj;

            for(int i = 0; i < 20; i++) {

                jsonObj = new JSONObject();
                try {
                    jsonObj.put("title",i+"JasonTitle");
                    jsonObj.put("author",i+"JasonAuthor");
                    jsonObj.put("content",i+"JasonContent");
                    jsonObj.put("date",i+"JasonDate");
                    jsonObj.put("classify",i+"JasonClassify");

                    JArray.put(jsonObj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Log.d(TAG,"測試的JSON" +JArray);

            jsonIn = JArray.toString();
        }
//        Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//
//            if(response.isSuccessful()) {
//                jsonIn = response.body().string();
//            }
//            else {
//                jsonIn = null;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Log.d(TAG, "jsonIn: " + jsonIn);

        return jsonIn;
    }
}
