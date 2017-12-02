package com.example.administrator.training3c_27112017;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.administrator.training3c_27112017.adapter.ListUserRecyclerViewAdapter;
import com.example.administrator.training3c_27112017.bindingadapter.BindingUtils;
import com.example.administrator.training3c_27112017.databinding.FragmentListUserBinding;
import com.example.administrator.training3c_27112017.interfaces.OnItemRecyclerViewClick;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.model.User;
import com.example.administrator.training3c_27112017.roomdb.entity.UserEntity;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListUserFragment extends Fragment implements OnItemRecyclerViewClick {

    private RecyclerView mRecyclerView;
    private ListUserRecyclerViewAdapter mListUserRecyclerViewAdapter;
    private GithubUserResponse userReponse;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private List<User> mUsers = new ArrayList<>();
    private Database mDatabase;
    private static final String TAG = "ListUserFragment";
    private CompositeDisposable mCompositeDisposable;
    private FragmentListUserBinding binding;
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mID = new ObservableField<>();
    public ObservableField<RecyclerView.LayoutManager> mManager = new ObservableField<>();

    public static ListUserFragment newInstant(GithubUserResponse userReponse) {
        ListUserFragment fragment = new ListUserFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_LIST_USER, userReponse);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false);
        binding.setListUserFragment(this);

        //        binding.setButtonInsert(binding.insertUserButton);
        View v = binding.getRoot();
        layoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity());

        initViews(v);

        getUserAndUpdateAdapter();

        //TODO lam loadmore recyclerview
        //        mListUserRecyclerViewAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
        //            @Override
        //            public void onLoadMore() {
        //                if (mUsers.size() <= 20){
        //                    mListUserRecyclerViewAdapter.updateData(mUsers);
        //                    new Handler().postDelayed(new Runnable() {
        //                        @Override
        //                        public void run() {
        ////                            mUsers.remove(mUsers.size() - 1);
        //                            mListUserRecyclerViewAdapter.notifyItemRemoved(mUsers.size());
        //                            int index = mUsers.size();
        //                            int end = index + 10;
        //                            for (int i = index; i < end; i++) {
        //                                mUsers.add(userReponse.getItems().get(i));
        //                            }
        //                            mListUserRecyclerViewAdapter.updateData(mUsers);
        //                            mListUserRecyclerViewAdapter.setLoaded();
        //                        }
        //                    },1000);
        //                } else {
        //                    Toast.makeText(getActivity(), "Load complete", Toast.LENGTH_SHORT)
        // .show();
        //                }
        //            }
        //        });
        initListeners();

        return v;
    }

    private void getUserAndUpdateAdapter() {
        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = mDatabase.getUserDAO()
                .getListUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserEntity>>() {
                    @Override
                    public void accept(List<UserEntity> users) throws Exception {
                        mListUserRecyclerViewAdapter.updateData(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void initListeners() {
        //        binding.getListUserFragment().mBtnInsert.setOnClickListener(this);
    }

    private void initViews(View v) {
        mDatabase = MainApplication.getDatabase();
        //        for (int i = 0; i < 10; i++) {
        //            mUsers.add(userReponse.getItems().get(i));
        //        }

        //        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity(),
        // mRecyclerView);
        //        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity());

        //        binding.listUserRecyclerView.setAdapter(mListUserRecyclerViewAdapter);
    }

    public void onClickInsertButton(View view) {
        insertUserToDB();
    }

    public void onClickHorizontalButton(View view) {
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mManager.set(layoutManager);
    }

    public void onClickVerticalButton(View view) {
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mManager.set(layoutManager);
    }

    public void onClickGridButton(View view) {
        mManager.set(gridLayoutManager);
    }

    private void insertUserToDB() {
        Completable.defer(new Callable<Completable>() {
            @Override
            public Completable call() throws Exception {
                return Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        String name = mUserName.get();
                        int id = Integer.parseInt(mID.get());
                        UserEntity user = new UserEntity();
                        user.setName(name);
                        user.setId(id);
                        mDatabase.getUserDAO().insertUser(user);
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getActivity(), "insert success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void onItemClicked(UserEntity user) {
        DetailUserFragment detailUserFragment = new DetailUserFragment();
        FragmentTransaction manager = getActivity().getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.container, detailUserFragment.newInstant(user.getId()));
        manager.addToBackStack(Constant.TAG_LIST_USER_FREGMENT);
        manager.commitAllowingStateLoss();
    }

    public ObservableField<RecyclerView.LayoutManager> getLayoutManager() {
        return mManager;
    }
}

