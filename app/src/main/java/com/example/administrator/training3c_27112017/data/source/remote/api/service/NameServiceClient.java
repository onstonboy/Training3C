package com.example.administrator.training3c_27112017.data.source.remote.api.service;

import android.app.Application;
import com.example.administrator.training3c_27112017.utils.Constant;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 12/05/17.
 */

public class NameServiceClient extends ServiceClient {

    private static NameApi mNameApiInstance;

    public static void initialize(@NonNull Application application){
        mNameApiInstance = createService(application, Constant.END_POINT_URL, NameApi.class);
    }

    public static NameApi getInstance() {
        if (mNameApiInstance == null) {
            throw new RuntimeException("Need call method NameServiceClient#initialize() first");
        }
        return mNameApiInstance;
    }
}
