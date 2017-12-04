package com.example.administrator.training3c_27112017.bindingadapter;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.administrator.training3c_27112017.EndlessRecyclerOnScrollListener;
import com.example.administrator.training3c_27112017.adapter.ListUserRecyclerViewAdapter;
import com.example.administrator.training3c_27112017.roomdb.entity.UserEntity;
import io.reactivex.Flowable;
import java.util.List;
import java.util.concurrent.Callable;
import org.reactivestreams.Publisher;

/**
 * Created by Administrator on 12/02/17.
 */

public final class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter({ "reyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "layoutManager" })
    public static void setlayoutManager(RecyclerView recyclerView,
            RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({ "onScroll", "getAdapter", "getListUser" })
    public static void onScroll(RecyclerView recyclerView,
            LinearLayoutManager layoutManager,RecyclerView.Adapter adapter, List<UserEntity> list) {
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.d("testbinder", "onLoadMore: " + list.size());
            }
        });
    }

    private static void getData(ListUserRecyclerViewAdapter adapter) {
        adapter.getBar().setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                for (int i = 0; i <= 30; i++) {
//                    mStringList.add("SampleText : " + mLoadedItems);
//                    mLoadedItems++;
//                }
//                mSampleAdapter.notifyDataSetChanged();
//                mActivityMainBinding.itemProgressBar.setVisibility(View.GONE);
            }
        }, 1500);
    }
}
