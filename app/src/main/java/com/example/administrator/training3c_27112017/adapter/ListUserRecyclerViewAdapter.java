package com.example.administrator.training3c_27112017.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.training3c_27112017.R;
import com.example.administrator.training3c_27112017.User;
import com.example.administrator.training3c_27112017.interfaces.OnItemRecyclerViewClick;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/27/17.
 */

public class ListUserRecyclerViewAdapter extends RecyclerView.Adapter<ListUserRecyclerViewAdapter.RecyclerViewHolder> {

    private List<User> mUsers = new ArrayList<>();
    private Context mContext;
    private OnItemRecyclerViewClick mOnItemRecyclerViewClick;

    public ListUserRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<User> users){
        if (users == null){
            return;
        }
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    public void setOnItemRecyclerViewClick(OnItemRecyclerViewClick onItemRecyclerViewClick) {
        mOnItemRecyclerViewClick = onItemRecyclerViewClick;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new RecyclerViewHolder(v, mOnItemRecyclerViewClick);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.binder(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtName;
        private OnItemRecyclerViewClick mOnItemRecyclerViewClick;
        private int position = 0 ;

        public RecyclerViewHolder(View itemView, final OnItemRecyclerViewClick onItemRecyclerViewClick) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.nameTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemRecyclerViewClick = onItemRecyclerViewClick;
                    mOnItemRecyclerViewClick.onItemClicked(position);
                }
            });
        }

        public void binder(int position) {
            mTxtName.setText(mUsers.get(position).getLogin());
            this.position = position;
        }
    }
}

