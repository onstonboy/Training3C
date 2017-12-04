package com.example.administrator.training3c_27112017;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Administrator on 12/02/17.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5;
    private int mPreviousTotal;
    private boolean isLoading = true;
    private int mFirstVisibleItem;
    private int mVisibleItemCount;
    private int mTotalItemCount;

    private int mCurrentPage = 0;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public void refreshData() {
        mPreviousTotal = 0;
        isLoading = true;
        mFirstVisibleItem = 0;
        mVisibleItemCount = 0;
        mTotalItemCount = 0;
        mCurrentPage = 0;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLinearLayoutManager.getItemCount();
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (isLoading && mTotalItemCount > mPreviousTotal) {
            isLoading = false;
            mPreviousTotal = mTotalItemCount;
        }
        if (!isLoading && (mTotalItemCount - mVisibleItemCount) <= (mFirstVisibleItem
                + VISIBLE_THRESHOLD)) {

            // Do something
            mCurrentPage++;

            onLoadMore(mCurrentPage);

            isLoading = true;
        }

        Log.d("chuong", "onScrolled: " + mFirstVisibleItem);
    }

    public abstract void onLoadMore(int currentPage);
}

