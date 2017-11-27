package com.example.administrator.training3c_27112017;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchDataFromUrlSuccess {

    private FetchDataFromUrl mFetchDataFromUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.github.com/search/users?per_page=50&q=abc";
        mFetchDataFromUrl = new FetchDataFromUrl(this);
        mFetchDataFromUrl.execute(url);
    }

    @Override
    public void onFetchDataSuccess(List<User> users) {
        UserReponse userReponse = new UserReponse();
        userReponse.setUsers(users);
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.add(R.id.container, ListUserFragment.newInstant(userReponse), "ListUserFragment");
        manager.commitAllowingStateLoss();
    }
}
