package com.example.administrator.training3c_27112017.data.source;

import com.example.administrator.training3c_27112017.data.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by Administrator on 12/05/17.
 */

public interface UserDataSource {

    interface RemoteDataSource{
        Observable<List<User>> searchUsers(String keyword, int limit);
    }
}
