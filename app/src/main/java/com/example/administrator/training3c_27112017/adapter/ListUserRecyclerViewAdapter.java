package com.example.administrator.training3c_27112017.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.training3c_27112017.R;
import com.example.administrator.training3c_27112017.User;
import com.example.administrator.training3c_27112017.interfaces.OnItemRecyclerViewClick;
import com.example.administrator.training3c_27112017.interfaces.OnLoadMoreListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/27/17.
 */

public class ListUserRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<User> mUsers = new ArrayList<>();
    private Context mContext;
    private OnItemRecyclerViewClick mOnItemRecyclerViewClick;
    private OnLoadMoreListener mOnLoadMoreListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private int visibleThreshold = 5;

    public ListUserRecyclerViewAdapter(Context context) {
        mContext = context;
//        final LinearLayoutManager layoutManager =
//                (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = layoutManager.getItemCount();
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                    if (mOnLoadMoreListener != null) {
//                        mOnLoadMoreListener.onLoadMore();
//                    }
//                    isLoading = true;
//                }
//            }
//        });
    }

    public void updateData(List<User> users) {
        if (users == null) {
            return;
        }
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnItemRecyclerViewClick(OnItemRecyclerViewClick onItemRecyclerViewClick) {
        mOnItemRecyclerViewClick = onItemRecyclerViewClick;
    }

//    @Override
//    public int getItemViewType(int position) {
//        return mUsers.get(position) == null ? VIEW_TYPE_ITEM : VIEW_TYPE_LOADING;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_ITEM) {
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_user, parent, false);
//            return new RecyclerViewHolder(v, mOnItemRecyclerViewClick);
//        } else if (viewType == VIEW_TYPE_LOADING) {
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_loading, parent, false);
//            return new LoadingViewHolder(v);
//        }
//        return  null;
        View v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_user, parent, false);
                    return new RecyclerViewHolder(v, mOnItemRecyclerViewClick);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof RecyclerViewHolder) {
//            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
//            recyclerViewHolder.binder(position);
//        } else if (holder instanceof LoadingViewHolder) {
//            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//            loadingViewHolder.mProgressBar.setIndeterminate(true);
//        }
        RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
        recyclerViewHolder.binder(position);
    }

    @Override
    public int getItemCount() {
//        return mUsers == null ? 0 : mUsers.size();
        return mUsers.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtName;
        private OnItemRecyclerViewClick mOnItemRecyclerViewClick;
        private int position = 0;

        public RecyclerViewHolder(View itemView,
                final OnItemRecyclerViewClick onItemRecyclerViewClick) {
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

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar mProgressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.loadMoreProgressBar);
        }
    }
}

