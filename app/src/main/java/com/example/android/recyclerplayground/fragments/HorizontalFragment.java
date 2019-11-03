package com.example.android.recyclerplayground.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recyclerplayground.InsetDecoration;
import com.example.android.recyclerplayground.adapters.SimpleAdapter;

public class HorizontalFragment extends RecyclerFragment {

    public static HorizontalFragment newInstance() {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 40;
    }

    @Override
    protected SimpleAdapter getAdapter() {
        return new SimpleAdapter();
    }
}
