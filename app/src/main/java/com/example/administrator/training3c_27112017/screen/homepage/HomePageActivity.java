package com.example.administrator.training3c_27112017.screen.homepage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.administrator.training3c_27112017.data.source.UserRepository;
import com.example.administrator.training3c_27112017.data.source.remote.UserRemoteDataSource;
import com.example.administrator.training3c_27112017.data.source.remote.api.service
        .NameServiceClient;
import com.example.administrator.training3c_27112017.databinding.ActivityHomepageBinding;
import com.example.administrator.training3c_27112017.screen.BaseActivity;
import com.example.administrator.training3c_27112017.R;

/**
 * HomePage Screen.
 */
public class HomePageActivity extends BaseActivity {

    private HomePageViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomePageAdapter homePageAdapter = new HomePageAdapter(this);

        mViewModel = new HomePageViewModel(homePageAdapter);
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(NameServiceClient.getInstance()));
        HomePageContract.Presenter presenter = new HomePagePresenter(mViewModel, userRepository);

        mViewModel.setPresenter(presenter);

        ActivityHomepageBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
