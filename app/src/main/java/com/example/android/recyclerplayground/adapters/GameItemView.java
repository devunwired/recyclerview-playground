package com.example.android.recyclerplayground.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.android.recyclerplayground.R;

public class GameItemView extends GridLayout {

    private TextView mHomeScore, mAwayScore;

    public GameItemView(Context context) {
        super(context);
    }

    public GameItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mHomeScore = (TextView) findViewById(R.id.text_score_home);
        mAwayScore = (TextView) findViewById(R.id.text_score_away);
    }

    @Override
    public String toString() {
        return mAwayScore.getText() + "v" + mHomeScore.getText()
                + ": " + getLeft() + "," + getTop()
                + ": " + getMeasuredWidth() + "x" + getMeasuredHeight();
    }
}
