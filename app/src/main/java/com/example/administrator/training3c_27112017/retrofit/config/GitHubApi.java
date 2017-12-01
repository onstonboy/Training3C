package com.example.administrator.training3c_27112017.retrofit.config;

import com.example.administrator.training3c_27112017.GithubUserResponse;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 11/28/17.
 */

public interface GitHubApi {
    @GET("search/users?per_page=50&q=abc")
    Call<GithubUserResponse> githubUser();

    @GET("search/users?per_page=50&q=abc")
    Observable<GithubUserResponse> getListUser();

    @GET("search/users?per_page=5&q=abc")
    Single<GithubUserResponse> getListUserSignle();

    @GET("search/users?per_page=50&q=abc")
    Maybe<GithubUserResponse> getListUserMaybe();

    @GET("search/users?per_page=50&q=abc")
    Flowable<GithubUserResponse> getListUserFlowable();

    @GET("search/users?per_page=50&q=abc")
    Completable getListUserCompletable();

    @GET("/users/{username}")
    Observable<GithubUserResponse> getUserName(@Path("username") String username);

}
