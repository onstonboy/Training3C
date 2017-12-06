package com.example.administrator.training3c_27112017.data.source.remote;

import com.example.administrator.training3c_27112017.data.source.remote.api.service.NameApi;

/**
 * Created by Administrator on 12/05/17.
 */

public abstract class BaseRemoteDataSource {

    NameApi mNameApi;

    public BaseRemoteDataSource(NameApi nameApi) {
        mNameApi = nameApi;
    }
}
