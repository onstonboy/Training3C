package com.example.administrator.training3c_27112017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUserFragment extends Fragment {

    private TextView mTxtName,mTxtId;
    private UserReponse userReponse;
    private int position = 0;

    public static DetailUserFragment newInstant(UserReponse userReponse, int position) {
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
        String name = userReponse.getUsers().get(position).getLogin();
        int id = userReponse.getUsers().get(position).getId();
        mTxtName.setText(name);
        mTxtId.setText(id+"");
        return v;
    }

    private void initViews(View v) {
        mTxtName = v.findViewById(R.id.nameUserTextView);
        mTxtId = v.findViewById(R.id.idUserTextView);
    }
}
