package com.example.administrator.training3c_27112017.screen.homepage;

import android.databinding.BaseObservable;
import android.view.View;
import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.screen.BaseRecyclerViewAdapter;

/**
 * Created by Administrator on 12/05/17.
 */

public class ItemHomePageViewModel extends BaseObservable {

    private User mUser;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> mItemClickListener;

    public ItemHomePageViewModel(User user,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> itemClickListener) {
        mUser = user;
        mItemClickListener = itemClickListener;
    }

    public String getUserLogin() {
        return mUser.getLogin();
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mUser);
    }
}
