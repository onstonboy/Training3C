package com.example.administrator.training3c_27112017.data.source.remote.api.service;

import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.data.model.UserGitHubResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 12/05/17.
 */

public interface NameApi {
    @GET("/search/users")
    Observable<UserGitHubResponse> searchGitHubUsers(@Query("per_page") int limit,
            @Query("q") String keyword);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
