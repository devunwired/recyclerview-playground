package com.example.android.recyclerplayground;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class StaticTwoWayFragment extends RecyclerFragment {

    public static StaticTwoWayFragment newInstance() {
        StaticTwoWayFragment fragment = new StaticTwoWayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaticGridLayoutManager();
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 5;
    }
}
