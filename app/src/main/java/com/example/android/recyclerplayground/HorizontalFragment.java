package com.example.android.recyclerplayground;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
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
public class HorizontalFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RecyclerView mList;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HorizontalFragment newInstance() {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HorizontalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horizontal, container, false);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);
        //Apply the layout parameters
        mList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mList.addItemDecoration(new InsetDecoration(getActivity()));

        SimpleAdapter adapter = new SimpleAdapter(SimpleAdapter.generateDummyData());
        adapter.setOnItemClickListener(this);
        mList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Clicked: "+position, Toast.LENGTH_SHORT).show();
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
