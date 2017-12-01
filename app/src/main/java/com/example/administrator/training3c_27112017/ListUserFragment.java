package com.example.administrator.training3c_27112017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.administrator.training3c_27112017.adapter.ListUserRecyclerViewAdapter;
import com.example.administrator.training3c_27112017.asynctask
        .InsertUserWhenClickOnRecyclerViewAsyncTask;
import com.example.administrator.training3c_27112017.interfaces.OnItemRecyclerViewClick;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.roomdb.entity.User;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListUserFragment extends Fragment
        implements OnItemRecyclerViewClick, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ListUserRecyclerViewAdapter mListUserRecyclerViewAdapter;
    private GithubUserResponse userReponse;
    private Button mBtnVertical, mBtnHorizontal, mBtnGrid;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private List<User> mUsers = new ArrayList<>();
    private Database mDatabase;
    private static final String TAG = "ListUserFragment";
    private CompositeDisposable mCompositeDisposable;
    private Button mBtnInsert;
    private EditText mEdtName, mEdtId;

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
        View v = inflater.inflate(R.layout.fragment_list_user, container, false);
        initViews(v);

        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = mDatabase.getUserDAO()
                .getListUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        mListUserRecyclerViewAdapter.updateData(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
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

    private void initListeners() {
        mBtnHorizontal.setOnClickListener(this);
        mBtnVertical.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mListUserRecyclerViewAdapter.setOnItemRecyclerViewClick(this);
        mBtnInsert.setOnClickListener(this);
    }

    private void initViews(View v) {
        mBtnHorizontal = v.findViewById(R.id.horizontalButton);
        mBtnVertical = v.findViewById(R.id.verticalButton);
        mBtnGrid = v.findViewById(R.id.gridButton);
        mRecyclerView = v.findViewById(R.id.listUserRecyclerView);
        mBtnInsert = v.findViewById(R.id.insertUserButton);
        mEdtName = v.findViewById(R.id.nameEditText);
        mEdtId = v.findViewById(R.id.idEditText);

        mDatabase = MainApplication.getDatabase();
        //        for (int i = 0; i < 10; i++) {
        //            mUsers.add(userReponse.getItems().get(i));
        //        }

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        //        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity(),
        // mRecyclerView);
        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity());

        mRecyclerView.setAdapter(mListUserRecyclerViewAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        DetailUserFragment detailUserFragment = new DetailUserFragment();
        FragmentTransaction manager = getActivity().getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.container, detailUserFragment.newInstant(position));
        manager.addToBackStack(Constant.TAG_LIST_USER_FREGMENT);
        manager.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verticalButton:
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                break;
            case R.id.horizontalButton:
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(layoutManager);
                break;
            case R.id.gridButton:
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.insertUserButton:
                insertUserToDB();
                break;
        }
    }

    private void insertUserToDB() {
        Completable.defer(new Callable<Completable>() {
            @Override
            public Completable call() throws Exception {
                return Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        String name = mEdtName.getText().toString();
                        int id = Integer.parseInt(mEdtId.getText().toString());
                        User user = new User(name, id);
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
}

