package com.example.android.recyclerplayground;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class TwoWayFragment extends Fragment implements AdapterView.OnItemClickListener,
        RecyclerView.OnScrollListener {

    RecyclerView mList;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TwoWayFragment newInstance() {
        TwoWayFragment fragment = new TwoWayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TwoWayFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCounterToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_twoway, container, false);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);
        mList.setLayoutManager(new GridLayoutManager());
//        mList.addItemDecoration(new InsetDecoration(getActivity()));
        mList.setOnScrollListener(this);

        SimpleAdapter adapter = new SimpleAdapter(SimpleAdapter.generateDummyData());
        adapter.setOnItemClickListener(this);
        mList.setAdapter(adapter);

        return rootView;
    }

    private Toast mCounterToast;

    @Override
    public void onScrollStateChanged(int newState) {

    }

    @Override
    public void onScrolled(int dx, int dy) {
        mCounterToast.setText(String.valueOf(mList.getChildCount()));
        mCounterToast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Clicked: "+position+", index "+mList.indexOfChild(view), Toast.LENGTH_SHORT).show();
    }

    private static class InsetDecoration extends RecyclerView.ItemDecoration {

        private int mInsets;

        public InsetDecoration(Context context) {

            mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            //We can supply forced insets for each item view here in the Rect
            outRect.set(mInsets, mInsets, mInsets, mInsets);
        }
    }
}
