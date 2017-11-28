package com.example.administrator.training3c_27112017;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.administrator.training3c_27112017.retrofit.config.GitHubApi;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubApi api = MainApplication.getGithubApi();
                api.githubUser().enqueue(new Callback<GithubUserResponse>() {
                    @Override
                    public void onResponse(Call<GithubUserResponse> call,
                            Response<GithubUserResponse> response) {
                        FragmentTransaction manager = getSupportFragmentManager()
         .beginTransaction();
                        manager.add(R.id.container, ListUserFragment.newInstant(response.body()
         ), "ListUserFragment");
                        manager.commitAllowingStateLoss();
                    }

                    @Override
                    public void onFailure(Call<GithubUserResponse> call, Throwable t) {

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
