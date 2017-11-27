package com.example.administrator.training3c_27112017;

import android.os.AsyncTask;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 11/27/17.
 */

public class FetchDataFromUrl extends AsyncTask<String, Void, List<User>> {

    private FetchDataFromUrlSuccess mFetchDataFromUrlSuccess;

    public FetchDataFromUrl(FetchDataFromUrlSuccess fetchDataFromUrlSuccess) {
        mFetchDataFromUrlSuccess = fetchDataFromUrlSuccess;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<User> doInBackground(String... strings) {
        try {
            String json = getJSONStringFromURL(strings[0]);
            GithubUserResponse userResponse = new Gson().fromJson(json, GithubUserResponse.class);
            return userResponse.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJSONStringFromURL(String string) throws IOException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(string);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        reader.close();

        String json = builder.toString();
        urlConnection.disconnect();
        return json;
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        if (users == null) {
            return;
        }
        mFetchDataFromUrlSuccess.onFetchDataSuccess(users);
    }
}
