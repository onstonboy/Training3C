package com.example.administrator.training3c_27112017.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}
