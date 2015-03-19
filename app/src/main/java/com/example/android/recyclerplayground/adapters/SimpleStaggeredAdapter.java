package com.example.android.recyclerplayground.adapters;

import android.view.View;

import com.example.android.recyclerplayground.R;

public class SimpleStaggeredAdapter extends SimpleAdapter {

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        super.onBindViewHolder(itemHolder, position);

        final View itemView = itemHolder.itemView;
        if (position % 4 == 0) {
            int height = itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.card_staggered_height);
            itemView.setMinimumHeight(height);
        } else {
            itemView.setMinimumHeight(0);
        }
    }
}
