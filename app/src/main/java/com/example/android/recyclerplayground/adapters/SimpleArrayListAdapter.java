package com.example.android.recyclerplayground.adapters;

import java.util.ArrayList;
import java.util.List;

public class SimpleArrayListAdapter extends SimpleAdapter {

    private ArrayList<GameItem> mItems;

    public SimpleArrayListAdapter() {
        mItems = new ArrayList<SimpleAdapter.GameItem>();
    }

    /*
     * A common adapter modification or reset mechanism. As with ListAdapter,
     * calling notifyDataSetChanged() will trigger the RecyclerView to update
     * the view. However, this method will not trigger any of the RecyclerView
     * animation features.
     */
    public void setItemCount(int count) {
        mItems.clear();
        mItems.addAll(generateDummyData(count));

        notifyDataSetChanged();
    }

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void addItem(int position) {
        if (position > mItems.size()) return;

        mItems.add(position, generateDummyItem());
        notifyItemInserted(position);
    }

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemRemoved(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void removeItem(int position) {
        if (position >= mItems.size()) return;

        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    protected SimpleAdapter.GameItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static List<SimpleAdapter.GameItem> generateDummyData(int count) {
        ArrayList<SimpleAdapter.GameItem> items = new ArrayList<SimpleAdapter.GameItem>();

        for (int i=0; i < count; i++) {
            items.add(new SimpleAdapter.GameItem("Losers", "Winners", i, i+5));
        }

        return items;
    }
}
