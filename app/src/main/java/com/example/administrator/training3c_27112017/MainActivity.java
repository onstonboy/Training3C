package com.example.administrator.training3c_27112017;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.administrator.training3c_27112017.asynctask.InsertUserToDBAsynctask;
import com.example.administrator.training3c_27112017.retrofit.config.GitHubApi;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.model.User;
import com.example.administrator.training3c_27112017.roomdb.entity.UserEntity;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Database mDatabase;
    private CompositeDisposable mCompositeDisposable;
    private List<UserEntity> userEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = MainApplication.getDatabase();
        GitHubApi api = MainApplication.getGithubApi();
        // retrofit lay du lieu tu githubapi


//        api.githubUser().enqueue(new Callback<GithubUserResponse>() {
//            @Override
//            public void onResponse(Call<GithubUserResponse> call,
//                    Response<GithubUserResponse> response) {
//                FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
//                manager.add(R.id.container, ListUserFragment.newInstant(response.body()),
//                        "ListUserFragment");
//                manager.commitAllowingStateLoss();
//            }
//
//            @Override
//            public void onFailure(Call<GithubUserResponse> call, Throwable t) {
//
//            }
//        });

        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = api.getListUserSignle()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GithubUserResponse>() {
                    @Override
                    public void accept(GithubUserResponse githubUserResponse) throws Exception {
                        doCovertUserToUserEntity(githubUserResponse);

                        doInsertListUser(userEntities);
                        doCreateListUserFragment();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "throwable: ");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void doCovertUserToUserEntity(GithubUserResponse githubUserResponse) {
        userEntities = new ArrayList<>();
        for (User user : githubUserResponse.getItems()) {
            UserEntity userEntity = new UserEntity();
            String name = user.getLogin();
            int id = user.getId();
            String avatarUrl = user.getAvatarUrl();
            userEntity.setName(name);
            userEntity.setId(id);
            userEntity.setAvatarUrl(avatarUrl);
            userEntities.add(userEntity);
        }
    }

    private void doCreateListUserFragment() {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.add(R.id.container, new ListUserFragment(),
                "ListUserFragment");
        manager.commitAllowingStateLoss();
    }

    private void doInsertListUser(List<UserEntity> items) {
        Completable.defer(new Callable<CompletableSource>() {
            @Override
            public CompletableSource call() throws Exception {
                return Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        mDatabase.getUserDAO().insertListUser(items);
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
              //  Toast.makeText(MainActivity.this, "Insert to db success", Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
