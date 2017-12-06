package com.example.administrator.training3c_27112017.screen.LoginActivity;

/**
 * Listens to user actions from the UI ({@link LoginActivityActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class LoginActivityPresenter implements LoginActivityContract.Presenter{

    private static final String TAG = LoginActivityPresenter.class.getName();

    private final LoginActivityContract.ViewModel mViewModel;

    public LoginActivityPresenter(LoginActivityContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
