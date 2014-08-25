package com.example.android.recyclerplayground;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class VerticalFragment extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static VerticalFragment newInstance() {
        VerticalFragment fragment = new VerticalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public VerticalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vertical, container, false);

        RecyclerView list = (RecyclerView) rootView.findViewById(R.id.section_list);
        //Apply the layout parameters
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //We must draw dividers ourselves
        list.addItemDecoration(new DividerDecoration(getActivity()));

        SimpleAdapter adapter = new SimpleAdapter(SimpleAdapter.generateDummyData());
        adapter.setOnItemClickListener(this);
        list.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Clicked: "+position, Toast.LENGTH_SHORT).show();
    }

    private static class DividerDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = { android.R.attr.listDivider };

        private Drawable mDivider;
        private int mInsets;

        public DividerDecoration(Context context) {
            TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();

            mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent) {
            drawVertical(c, parent);
        }

        /** Draw dividers underneath each child view */
        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin + mInsets;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            //We can supply forced insets for each item view here in the Rect
            outRect.set(mInsets, mInsets, mInsets, mInsets);
        }
    }
}
