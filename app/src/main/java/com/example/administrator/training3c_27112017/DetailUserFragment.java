package com.example.administrator.training3c_27112017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.administrator.training3c_27112017.retrofit.config.GitHubApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUserFragment extends Fragment {

    private TextView mTxtName,mTxtId;
    private ImageView mImageView;
    private GithubUserResponse userReponse;
    private int position = 0;
    private CompositeDisposable mCompositeDisposable;
    private GitHubApi api;
    private static final String TAG = "DetailUserFragment";
    private String name;

    public static DetailUserFragment newInstant(GithubUserResponse userReponse, int position) {
        DetailUserFragment detailUserFragment = new DetailUserFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_LIST_USER, userReponse);
        bundle.putInt(Constant.EXTRA_POSITION_USER, position);
        detailUserFragment.setArguments(bundle);
        return detailUserFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail_user, container, false);
        initViews(v);

        userReponse = getArguments().getParcelable(Constant.EXTRA_LIST_USER);
        position = getArguments().getInt(Constant.EXTRA_POSITION_USER);

        name = userReponse.getItems().get(position).getLogin();
        int id = userReponse.getItems().get(position).getId();

        // demo RxJava2 + Retrofit
        //test flatmap - flatmap vs filter
        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = api.getListUser()
                .flatMap(new Function<GithubUserResponse, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> apply(GithubUserResponse gitHubUserResponse)
                            throws Exception {
                        return Observable.just(gitHubUserResponse.getItems());
                    }
                })
                .flatMap(new Function<List<User>, Observable<User>>() {
                    @Override
                    public Observable<User> apply(List<User> users) throws Exception {
                        return Observable.fromIterable(users);
                    }
                })
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {

                        return user.getLogin().equals(name);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mTxtName.setText(user.getLogin());
                        mTxtId.setText(user.getId()+"");
                        Glide.with(getActivity()).load(user.getAvatarUrl()).into(mImageView);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TEST", "error: ", throwable);
                    }
                });
        mCompositeDisposable.add(disposable);

        return v;
    }

    private void initViews(View v) {
        mTxtName = v.findViewById(R.id.nameUserTextView);
        mTxtId = v.findViewById(R.id.idUserTextView);
        mImageView = v.findViewById(R.id.avatarImageView);
        api = MainApplication.getGithubApi();
    }


}
