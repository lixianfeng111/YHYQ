package com.ps.mrcyclerview.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.ps.mrcyclerview.RecyclerAdapter;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


public abstract class Y_DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "Y_DividerItemDecoration";
    private Paint mPaint;

    private Context context;

    public Y_DividerItemDecoration(Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //left, top, right, bottom
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();

            Y_Divider divider = getDivider(itemPosition);
            if (divider == null) {
                divider = new Y_DividerBuilder().create();
            }

            if (divider.getLeftSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getLeftSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getLeftSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getLeftSideLine().getEndPaddingDp());
                drawChildLeftVertical(child, c, parent, divider.getLeftSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getTopSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getTopSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getTopSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getTopSideLine().getEndPaddingDp());
                drawChildTopHorizontal(child, c, parent, divider.topSideLine.getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getRightSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getRightSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().getEndPaddingDp());
                drawChildRightVertical(child, c, parent, divider.getRightSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getBottomSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getBottomSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().getEndPaddingDp());
                drawChildBottomHorizontal(child, c, parent, divider.getBottomSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
        }
    }

    private void drawChildBottomHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0??????==0??????
            //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    private void drawChildTopHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0??????==0??????
            //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int bottom = child.getTop() - params.topMargin;
        int top = bottom - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    private void drawChildLeftVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0??????==0??????
            //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int right = child.getLeft() - params.leftMargin;
        int left = right - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    private void drawChildRightVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0??????==0??????
            //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int left = child.getRight() + params.rightMargin;
        int right = left + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //outRect ??????????????????????????????Rect?????????outRect?????????????????????left,right,top,bottom???????????????,
        //????????????left,right,top,bottom?????????????????????
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        Y_Divider divider = getDivider(itemPosition);
        if (divider == null) {
            divider = new Y_DividerBuilder().create();
        }

        if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager sglm = (StaggeredGridLayoutManager) parent.getLayoutManager();
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            RecyclerAdapter adapter = (RecyclerAdapter) parent.getAdapter();
            int spanCount = sglm.getSpanCount();
            int span = Dp2Px.convert(context,divider.getLeftSideLine().getWidthDp());

            if (lp.getSpanIndex() % spanCount == 0) {
                outRect.left = span;
                outRect.right = span / 2;
            } else if (lp.getSpanIndex() % spanCount == spanCount - 1){
                outRect.left = span / 2;
                outRect.right = span;
            } else {
                outRect.left = span / 2;
                outRect.right = span / 2;
            }

            if (adapter.isContentView(itemPosition)){
                int position = itemPosition - adapter.getHeaderSize();
                if (position < spanCount){
                    outRect.top = 0;
                } else {
                    outRect.top = span;
                }
            }

        } else {


//        Log.d(TAG,"childCount === " + childCount + "    itemPosition === " + itemPosition);



            int left = divider.getLeftSideLine().isHave() ? Dp2Px.convert(context, divider.getLeftSideLine().getWidthDp()) : 0;
            int top = divider.getTopSideLine().isHave() ? Dp2Px.convert(context, divider.getTopSideLine().getWidthDp()) : 0;
            int right = divider.getRightSideLine().isHave() ? Dp2Px.convert(context, divider.getRightSideLine().getWidthDp()) : 0;
            int bottom = divider.getBottomSideLine().isHave() ? Dp2Px.convert(context, divider.getBottomSideLine().getWidthDp()) : 0;

            outRect.set(left, top, right, bottom);
        }

    }


    public abstract @Nullable
    Y_Divider getDivider(int itemPosition);


}

















