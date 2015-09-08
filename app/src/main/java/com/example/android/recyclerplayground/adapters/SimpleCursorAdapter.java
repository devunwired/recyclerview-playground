package com.example.android.recyclerplayground.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.android.recyclerplayground.database.GameContentProvider;

import java.util.ArrayList;
import java.util.List;

public class SimpleCursorAdapter extends SimpleAdapter {


    public SimpleCursorAdapter(Context context) {
        mContext = context;
    }

    private int getDBIdFromArrayPosition(int position) {
        return position + 1;
    }

    /*
     * A common adapter modification or reset mechanism. As with ListAdapter,
     * calling notifyDataSetChanged() will trigger the RecyclerView to update
     * the view. However, this method will not trigger any of the RecyclerView
     * animation features.
     */
    public void setItemCount(int count) {
        generateDummyData(count, mContext);

        notifyDataSetChanged();
    }

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void addItem(int zeroIndexPosition) {
        if (mContext == null) {
            return;
        }

        mContext.getContentResolver().insert(GameContentProvider.CONTENT_URI, convertGameIntoContentValues(generateDummyItem(), zeroIndexPosition));
        notifyItemInserted(zeroIndexPosition);
    }

    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemRemoved(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void removeItem(int zeroIndexPosition) {
        if (mContext == null) return;

        Uri toBeDeleted = Uri.withAppendedPath(GameContentProvider.CONTENT_URI, getDBIdFromArrayPosition(zeroIndexPosition) + "");

        int deletedCount = mContext.getContentResolver().delete(toBeDeleted, null, null);
        Toast.makeText(mContext, "Deleted " + deletedCount + " items.", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(getDBIdFromArrayPosition(zeroIndexPosition));
    }

    @Override
    protected SimpleAdapter.GameItem getItem(int zeroIndexPosition) {
        if (mContext == null) {
            return null;
        }
        // Using DB indicies, not array indicies
        int id = getDBIdFromArrayPosition(zeroIndexPosition);

        String[] projection = {GameContentProvider.KEY_ID, GameContentProvider.KEY_HOME_TEAM, GameContentProvider.KEY_AWAY_TEAM, GameContentProvider.KEY_HOME_TEAM_SCORE, GameContentProvider.KEY_AWAY_TEAM_SCORE, GameContentProvider.KEY_PRIORITY, GameContentProvider.KEY_DATE};
        Uri toBeFound = Uri.withAppendedPath(GameContentProvider.CONTENT_URI, id + "");
        Cursor cursor = mContext.getContentResolver().query(toBeFound, projection, null, null, null);

        if (cursor == null) {
            Log.e(SimpleCursorAdapter.class.getSimpleName(), "The cursor is null, maybe your provider is badly declared in the AndroidManifest?");
            return null;
        }
        if (cursor.getCount() == 0) {
            Log.d(SimpleCursorAdapter.class.getSimpleName(), "Unable to find item " + toBeFound);
            return null;
        }
        cursor.moveToFirst();

        GameItem game = new GameItem(cursor.getString(cursor.getColumnIndexOrThrow(GameContentProvider.KEY_HOME_TEAM)),
                cursor.getString(cursor.getColumnIndexOrThrow(GameContentProvider.KEY_AWAY_TEAM)),
                cursor.getInt(cursor.getColumnIndexOrThrow(GameContentProvider.KEY_HOME_TEAM_SCORE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(GameContentProvider.KEY_AWAY_TEAM_SCORE)));

        cursor.close();
        cursor = null;

        return game;
    }

    @Override
    public int getItemCount() {
        if (mContext == null) {
            return 0;
        }
        String[] projection = {GameContentProvider.KEY_ID};
        CursorLoader loader = new CursorLoader(mContext, GameContentProvider.CONTENT_URI, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor == null) {
            Log.e(SimpleCursorAdapter.class.getSimpleName(), "The cursor is null, maybe your provider is not declared in the AndroidManifest?");
            loader = null;
            return 0;
        }
        int itemCount = cursor.getCount();
        cursor.close();
        cursor = null;
        loader = null;

        return itemCount;
    }

    public static ContentValues convertGameIntoContentValues(SimpleAdapter.GameItem game, int zeroIndexPosition) {
        ContentValues values = new ContentValues();
        if (zeroIndexPosition > -1) {
            values.put(GameContentProvider.KEY_PRIORITY, zeroIndexPosition + "");
            game.homeTeam = game.homeTeam + " " + zeroIndexPosition;
        }
        values.put(GameContentProvider.KEY_HOME_TEAM, game.homeTeam);
        values.put(GameContentProvider.KEY_AWAY_TEAM, game.awayTeam);
        values.put(GameContentProvider.KEY_HOME_TEAM_SCORE, game.homeScore);
        values.put(GameContentProvider.KEY_AWAY_TEAM_SCORE, game.awayScore);
        values.put(GameContentProvider.KEY_DATE, System.currentTimeMillis());
        return values;
    }

    public static List<SimpleAdapter.GameItem> generateDummyData(int count, Context context) {
        if (context == null) {
            return new ArrayList<SimpleAdapter.GameItem>();
        }

        // Check to see if we have data
        String[] projection = {GameContentProvider.KEY_ID};
        CursorLoader loader = new CursorLoader(context, GameContentProvider.CONTENT_URI, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (cursor != null && cursor.getCount() >= count) {
            cursor.close();
            cursor = null;
            loader = null;
            return new ArrayList<SimpleAdapter.GameItem>();
        }

        cursor.close();
        cursor = null;
        loader = null;

        // Create some data
        SimpleAdapter.GameItem game;
        ContentValues values;
        for (int i = 0; i < count; i++) {
            game = generateDummyItem();
            values = convertGameIntoContentValues(game, i);
            context.getContentResolver().insert(GameContentProvider.CONTENT_URI, values);
        }

        return new ArrayList<SimpleAdapter.GameItem>();
    }
}
