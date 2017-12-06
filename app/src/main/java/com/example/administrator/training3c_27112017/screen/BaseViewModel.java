package com.example.administrator.training3c_27112017.screen;

/**
 * Created by Administrator on 12/06/17.
 */

public interface BaseViewModel<T extends BasePresenter> {

    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
