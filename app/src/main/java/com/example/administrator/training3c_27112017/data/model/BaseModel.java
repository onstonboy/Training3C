package com.example.administrator.training3c_27112017.data.model;

import com.google.gson.Gson;

/**
 * Created by Administrator on 12/05/17.
 */

public abstract class BaseModel implements Cloneable {

    public BaseModel clone() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), this.getClass());
    }
}
