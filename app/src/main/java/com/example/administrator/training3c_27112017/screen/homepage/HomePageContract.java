package com.example.administrator.training3c_27112017.screen.homepage;

import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.screen.BasePresenter;
import com.example.administrator.training3c_27112017.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomePageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onSearchUsersSuccess(List<User> users);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void onItemUserClicked(User user);

        void searchUsers(String keyWord, int limit);
    }
}
