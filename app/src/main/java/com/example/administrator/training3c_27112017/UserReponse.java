package com.example.administrator.training3c_27112017;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by Administrator on 11/27/17.
 */

public class UserReponse implements Parcelable {
    private List<User> mUsers;

    protected UserReponse(Parcel in) {
        mUsers = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<UserReponse> CREATOR = new Creator<UserReponse>() {
        @Override
        public UserReponse createFromParcel(Parcel in) {
            return new UserReponse(in);
        }

        @Override
        public UserReponse[] newArray(int size) {
            return new UserReponse[size];
        }
    };

    public UserReponse() {
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mUsers);
    }
}
