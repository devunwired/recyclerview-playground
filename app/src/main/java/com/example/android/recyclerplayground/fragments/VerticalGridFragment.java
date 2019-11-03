package com.example.android.recyclerplayground.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recyclerplayground.InsetDecoration;
import com.example.android.recyclerplayground.adapters.SimpleAdapter;

public class VerticalGridFragment extends RecyclerFragment {

    public static VerticalGridFragment newInstance() {
        VerticalGridFragment fragment = new VerticalGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter() {
        return new SimpleAdapter();
    }
}
