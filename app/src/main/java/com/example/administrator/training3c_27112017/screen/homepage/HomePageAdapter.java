package com.example.administrator.training3c_27112017.screen.homepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.administrator.training3c_27112017.R;
import com.example.administrator.training3c_27112017.data.model.User;
import com.example.administrator.training3c_27112017.databinding.ItemUserBinding;
import com.example.administrator.training3c_27112017.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/05/17.
 */

public class HomePageAdapter extends BaseRecyclerViewAdapter<HomePageAdapter.ItemViewHolder> {

    private List<User> mUsers;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> mItemClickListener;

    protected HomePageAdapter(@NonNull Context context) {
        super(context);
        mUsers = new ArrayList<>();
    }

    public void updateData(List<User> users) {
        if (users == null) {
            return;
        }
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_user, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<User> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    /**
     * ItemViewHolder
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> mItemClickListener;

        ItemViewHolder(ItemUserBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(User user) {
            mBinding.setViewModel(new ItemHomePageViewModel(user, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
