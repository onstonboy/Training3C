package com.example.administrator.training3c_27112017.data.source;

import com.example.administrator.training3c_27112017.data.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Administrator on 12/05/17.
 */

public class UserRepository {

    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public UserRepository(UserDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<User>> searchUsers(String keyWord, int limit){
        return mRemoteDataSource.searchUsers(keyWord, limit);
    }
}
