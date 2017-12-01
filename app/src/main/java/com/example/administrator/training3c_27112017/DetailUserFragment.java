package com.example.administrator.training3c_27112017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.administrator.training3c_27112017.retrofit.config.GitHubApi;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.roomdb.entity.User;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.reactivestreams.Publisher;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUserFragment extends Fragment {

    private TextView mTxtName, mTxtId, mTxtDetail;
    private ImageView mImageView;
    private GithubUserResponse userReponse;
    private int position = 0;
    private CompositeDisposable mCompositeDisposable;
    private GitHubApi api;
    private static final String TAG = "DetailUserFragment";
    private String name;
    private Database mDatabase;

    public static DetailUserFragment newInstant( int position) {
        DetailUserFragment detailUserFragment = new DetailUserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.EXTRA_POSITION_USER, position);
        detailUserFragment.setArguments(bundle);
        return detailUserFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail_user, container, false);
        initViews(v);

        position = getArguments().getInt(Constant.EXTRA_POSITION_USER);

        mDatabase = MainApplication.getDatabase();


//        mDatabase.getUserDAO()
//                .getListUser()
//                .flatMap(new Function<List<User>, Flowable<Integer>>() {
//                    @Override
//                    public Flowable<Integer> apply(List<User> users) throws Exception {
//                        return Flowable.just(users.size());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Toast.makeText(getActivity(), String.valueOf(integer), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d(TAG, "throwable: ");
//                    }
//                });

        // demo RxJava2 + Retrofit
        //test flatmap - flatmap vs filter
                mCompositeDisposable = new CompositeDisposable();

                    Disposable disposable = mDatabase.getUserDAO().getListUser()
                        .flatMap(new Function<List<User>, Flowable<User>>() {
                            @Override
                            public Flowable<User> apply(List<User> users) throws Exception {
                                return Flowable.just(users.get(position));
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                mTxtName.setText(user.getLogin());
                                mTxtId.setText(String.valueOf(user.getId()));
                                Glide.with(getActivity()).load(user.getAvatarUrl()).into
         (mImageView);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
                mCompositeDisposable.add(disposable);

        return v;
    }

    private void initViews(View v) {
        mTxtName = v.findViewById(R.id.nameUserTextView);
        mTxtId = v.findViewById(R.id.idUserTextView);
        mTxtDetail = v.findViewById(R.id.detailUserTextView);
        mImageView = v.findViewById(R.id.avatarImageView);
        api = MainApplication.getGithubApi();
    }
}
