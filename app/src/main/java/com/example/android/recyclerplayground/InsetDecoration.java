package com.example.android.recyclerplayground;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * ItemDecoration implementation that applies an inset margin
 * around each child of the RecyclerView. The inset value is controlled
 * by a dimension resource.
 */
public class InsetDecoration extends RecyclerView.ItemDecoration {

    private final int mInsets;

    public InsetDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        //We can supply forced insets for each item view here in the Rect
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
