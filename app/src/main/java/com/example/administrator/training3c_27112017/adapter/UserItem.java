package com.example.administrator.training3c_27112017.adapter;

import android.view.View;
import com.example.administrator.training3c_27112017.interfaces.OnItemRecyclerViewClick;
import com.example.administrator.training3c_27112017.roomdb.entity.UserEntity;

/**
 * Created by Administrator on 12/01/17.
 */

public class UserItem {
    private OnItemRecyclerViewClick mOnItemRecyclerViewClick;
    private UserEntity user;

    public UserItem() {
    }

    public UserItem(OnItemRecyclerViewClick onItemRecyclerViewClick, UserEntity user) {
        mOnItemRecyclerViewClick = onItemRecyclerViewClick;
        this.user = user;
    }

    public void setOnItemRecyclerViewClick(OnItemRecyclerViewClick onItemRecyclerViewClick) {
        mOnItemRecyclerViewClick = onItemRecyclerViewClick;
    }

    public void onClickItem(View view){
        if (mOnItemRecyclerViewClick == null){
            return;
        } else {
            mOnItemRecyclerViewClick.onItemClicked(user);
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
