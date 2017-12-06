package com.example.administrator.training3c_27112017.screen.homepage;

import android.util.Log;
import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.data.source.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link HomePageActivity}), retrieves the data and updates
 * the UI as required.
 */
final class HomePagePresenter implements HomePageContract.Presenter {
    private static final String TAG = HomePagePresenter.class.getName();

    private final HomePageContract.ViewModel mViewModel;
    private UserRepository mUserRepository;
    private CompositeDisposable mCompositeDisposable;
    private HomePageContract.Presenter mPresenter;

    HomePagePresenter(HomePageContract.ViewModel viewModel, UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        searchUsers("abc", 50);
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onItemUserClicked(User user) {
//                mPresenter.onItemUserClicked(user);
        Log.d(TAG, "onItemRecyclerViewClick: " + user.getLogin());
    }

    @Override
    public void searchUsers(String keyWord, int limit) {
        Disposable disposable = mUserRepository.searchUsers(keyWord, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        mViewModel.onSearchUsersSuccess(users);
                        Log.d(TAG, "accept: " + users.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
