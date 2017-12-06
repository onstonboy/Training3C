package com.example.administrator.training3c_27112017;

import android.app.Application;
import com.example.administrator.training3c_27112017.data.source.remote.api.service
        .NameServiceClient;

/**
 * Created by Administrator on 12/05/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NameServiceClient.initialize(this);
    }
}
