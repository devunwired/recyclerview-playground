package com.example.android.recyclerplayground;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VerticalItemHolder> {

    private ArrayList<GameItem> mItems;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public SimpleAdapter(List<GameItem> items) {
        mItems = new ArrayList<GameItem>();
        mItems.addAll(items);
    }

    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_match_item, container, false);

        return new VerticalItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        GameItem item = mItems.get(position);

        itemHolder.setAwayScore(String.valueOf(item.awayScore));
        itemHolder.setHomeScore(String.valueOf(item.homeScore));

        itemHolder.setAwayName(item.awayTeam);
        itemHolder.setHomeName(item.homeTeam);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(VerticalItemHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getPosition(), itemHolder.getItemId());
        }
    }

    public static class GameItem {
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

    public static List<SimpleAdapter.GameItem> generateDummyData() {
        ArrayList<SimpleAdapter.GameItem> items = new ArrayList<SimpleAdapter.GameItem>();

        for (int i=0; i < 200; i++) {
            items.add(new SimpleAdapter.GameItem("Losers", "Winners", i, i+5));
        }

        return items;
    }
}
