package com.example.administrator.training3c_27112017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.training3c_27112017.adapter.ListUserRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListUserFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListUserRecyclerViewAdapter mListUserRecyclerViewAdapter;

    public static ListUserFragment newInstant(UserReponse userReponse) {
        ListUserFragment fragment = new ListUserFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_LIST_USER, userReponse);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_user, container, false);
        UserReponse userReponse = getArguments().getParcelable(Constant.EXTRA_LIST_USER);
        mRecyclerView = v.findViewById(R.id.listUserRecyclerView);
        mListUserRecyclerViewAdapter = new ListUserRecyclerViewAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mListUserRecyclerViewAdapter);
        mListUserRecyclerViewAdapter.updateData(userReponse.getUsers());
        return v;
    }
}

