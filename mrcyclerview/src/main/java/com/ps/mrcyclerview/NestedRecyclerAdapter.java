package com.ps.mrcyclerview;

import android.content.Context;

/**
 * Created by PengSong on 18/11/21.
 */

public class NestedRecyclerAdapter extends RecyclerAdapter {

    public NestedRecyclerAdapter(Context mContext) {
        super(mContext);
    }

    public void addContentLayout(int contentLayoutRes, NestedItemViewConvert convert) {
        super.addContentLayout(contentLayoutRes, convert,false);

    }
}
