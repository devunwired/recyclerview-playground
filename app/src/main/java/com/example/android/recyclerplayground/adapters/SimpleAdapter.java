package com.example.android.recyclerplayground.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.android.recyclerplayground.R;

import java.util.List;
import java.util.Random;

public abstract class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VerticalItemHolder> {

    protected AdapterView.OnItemClickListener mOnItemClickListener;

    /*
     * A common adapter modification or reset mechanism. As with ListAdapter,
     * calling notifyDataSetChanged() will trigger the RecyclerView to update
     * the view. However, this method will not trigger any of the RecyclerView
     * animation features.
     */
    protected abstract void setItemCount(int count);

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    protected abstract void addItem(int position);

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemRemoved(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    protected abstract void removeItem(int position);

    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_match_item, container, false);

        return new VerticalItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        GameItem item = this.getItem(position);

        itemHolder.setAwayScore(String.valueOf(item.awayScore));
        itemHolder.setHomeScore(String.valueOf(item.homeScore));

        itemHolder.setAwayName(item.awayTeam);
        itemHolder.setHomeName(item.homeTeam);
    }

    protected abstract GameItem getItem(int position);

    @Override
    public abstract int getItemCount();

    protected void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(VerticalItemHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    protected static class GameItem {
        public String homeTeam;
        public String awayTeam;
        public int homeScore;
        public int awayScore;

        public GameItem(String homeTeam, String awayTeam, int homeScore, int awayScore) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }
    }

    public static class VerticalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mHomeScore, mAwayScore;
        private TextView mHomeName, mAwayName;

        private SimpleAdapter mAdapter;

        public VerticalItemHolder(View itemView, SimpleAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            mAdapter = adapter;

            mHomeScore = (TextView) itemView.findViewById(R.id.text_score_home);
            mAwayScore = (TextView) itemView.findViewById(R.id.text_score_away);
            mHomeName = (TextView) itemView.findViewById(R.id.text_team_home);
            mAwayName = (TextView) itemView.findViewById(R.id.text_team_away);
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }

        public void setHomeScore(CharSequence homeScore) {
            mHomeScore.setText(homeScore);
        }

        public void setAwayScore(CharSequence awayScore) {
            mAwayScore.setText(awayScore);
        }

        public void setHomeName(CharSequence homeName) {
            mHomeName.setText(homeName);
        }

        public void setAwayName(CharSequence awayName) {
            mAwayName.setText(awayName);
        }
    }

    public static GameItem generateDummyItem() {
        Random random = new Random();
        return new GameItem("Upset Home", "Upset Away",
                random.nextInt(100),
                random.nextInt(100) );
    }

    protected abstract List<SimpleAdapter.GameItem> generateDummyData(int count);
}
