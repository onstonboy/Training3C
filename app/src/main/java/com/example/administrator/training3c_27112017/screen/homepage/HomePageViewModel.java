package com.example.administrator.training3c_27112017.screen.homepage;

import android.databinding.BaseObservable;
import android.util.Log;
import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.screen.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * Exposes the data to be used in the HomePage screen.
 */

public class HomePageViewModel extends BaseObservable implements HomePageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> {

    private HomePageContract.Presenter mPresenter;
    private static final String TAG = "HomePageViewModel";
    private HomePageAdapter mAdapter;

    public HomePageViewModel(HomePageAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
    }

    public HomePageAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onItemRecyclerViewClick(User item) {
        mPresenter.onItemUserClicked(item);
//        Log.d(TAG, "onItemRecyclerViewClick: ");
    }

    @Override
    public void onSearchUsersSuccess(List<User> users) {
        mAdapter.updateData(users);
//        Log.d(TAG, "onSearchUsersSuccess: size " + users.size());
    }
}
