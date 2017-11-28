package com.example.administrator.training3c_27112017;

import android.app.Application;
import com.example.administrator.training3c_27112017.retrofit.config.GitHubApi;
import com.example.administrator.training3c_27112017.retrofit.config.ServiceGenerators;

/**
 * Created by Administrator on 11/28/17.
 */

public class MainApplication extends Application {

    private static GitHubApi mGithubApi;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mGithubApi == null){
            mGithubApi = ServiceGenerators.createApiService(this);
        }
    }

    public static GitHubApi getGithubApi() {
        return mGithubApi;
    }
}
