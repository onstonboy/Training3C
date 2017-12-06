package com.example.administrator.training3c_27112017.data.source.remote;

import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.data.model.UserGitHubResponse;
import com.example.administrator.training3c_27112017.data.source.UserDataSource;
import com.example.administrator.training3c_27112017.data.source.remote.api.service.NameApi;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by Administrator on 12/05/17.
 */

public class UserRemoteDataSource extends BaseRemoteDataSource
        implements UserDataSource.RemoteDataSource {
    public UserRemoteDataSource(NameApi nameApi) {
        super(nameApi);
    }

    @Override
    public Observable<List<User>> searchUsers(String keyword, int limit) {
        return mNameApi.searchGitHubUsers(limit, keyword)
                .flatMap(new Function<UserGitHubResponse, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> apply(UserGitHubResponse userGitHubResponse)
                            throws Exception {
                        if (userGitHubResponse != null){
                            return Observable.just(userGitHubResponse.getItems());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }
}
