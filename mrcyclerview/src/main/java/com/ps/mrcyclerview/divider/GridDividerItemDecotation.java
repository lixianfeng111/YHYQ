package com.ps.mrcyclerview.divider;

import android.content.Context;

import com.ps.mrcyclerview.IAdapter;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by PengSong on 18/6/9.
 */

public class GridDividerItemDecotation extends Y_DividerItemDecoration {

    private int dividerWidth;
    private @ColorInt
    int dividerColor;
    private GridLayoutManager glm;
    private RecyclerView mRecyclerView;

    private int count = 0;

    public GridDividerItemDecotation(Context context) {
        super(context);
    }

    public GridDividerItemDecotation(RecyclerView recyclerView, int dividerWidth, int dividerColor) {
        super(recyclerView.getContext());
        mRecyclerView = recyclerView;
        this.dividerWidth = dividerWidth;
        this.dividerColor = dividerColor;
        this.glm = (GridLayoutManager) recyclerView.getLayoutManager();
    }

    @Nullable
    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        IAdapter adapter = (IAdapter) mRecyclerView.getAdapter();
        int spanCount = glm.getSpanCount();
        if (adapter.isHeaderView(itemPosition)) {
            divider = new Y_DividerBuilder()
                    .setBottomSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                    .create();
        } else if (adapter.isContentView(itemPosition)) {
            int position = itemPosition - adapter.getHeaderSize();
            if (position % spanCount == 0) {
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                        .setRightSideLine(dividerWidth != 0, dividerColor, dividerWidth / 2, 0, 0)
                        .setBottomSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                        .create();
            } else if (position % spanCount == spanCount - 1){
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(dividerWidth != 0, dividerColor, dividerWidth / 2, 0, 0)
                        .setRightSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                        .setBottomSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                        .create();
            } else {
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(dividerWidth != 0, dividerColor, dividerWidth / 2, 0, 0)
                        .setRightSideLine(dividerWidth != 0, dividerColor, dividerWidth / 2, 0, 0)
                        .setBottomSideLine(dividerWidth != 0, dividerColor, dividerWidth, 0, 0)
                        .create();
            }
        }

        return divider;
    }
}
