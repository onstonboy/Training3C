package com.example.administrator.training3c_27112017.screen.LoginActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.administrator.training3c_27112017.R;
import com.example.administrator.training3c_27112017.databinding.ActivityLoginactivityBinding;
import com.example.administrator.training3c_27112017.screen.BaseActivity;


/**
 * LoginActivity Screen.
 */
public class LoginActivityActivity extends BaseActivity {

    private LoginActivityContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new LoginActivityViewModel();

        LoginActivityContract.Presenter presenter = new LoginActivityPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityLoginactivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_loginactivity);
        binding.setViewModel((LoginActivityViewModel) mViewModel);
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
