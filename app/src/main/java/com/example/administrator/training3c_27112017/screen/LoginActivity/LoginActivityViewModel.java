package com.example.administrator.training3c_27112017.screen.LoginActivity;

/**
 * Exposes the data to be used in the LoginActivity screen.
 */

public class LoginActivityViewModel implements LoginActivityContract.ViewModel{

    private LoginActivityContract.Presenter mPresenter;

    public LoginActivityViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(LoginActivityContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
