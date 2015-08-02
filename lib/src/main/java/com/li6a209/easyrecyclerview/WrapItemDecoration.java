package com.li6a209.easyrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 6a209 on 15/7/27.
 */
class WrapItemDecoration extends RecyclerView.ItemDecoration {
    private RecyclerView.ItemDecoration mOriginItemDecoration;
    private int mHeaderCount;
    private int mFooterCount;

    public WrapItemDecoration(RecyclerView.ItemDecoration itemDecoration, int headerCount, int footerCount){
        mOriginItemDecoration = itemDecoration;
        mHeaderCount = headerCount;
        mFooterCount = footerCount;
    }

    public void setHeaderCount(int count){
        mHeaderCount = count;
    }

    public void setFooterCount(int count){
        mFooterCount = count;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        int adapterCount = parent.getAdapter().getItemCount() + mHeaderCount + mFooterCount;
        for(int i = 0; i < count; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            int position = params.getViewPosition();
            if(position >= mHeaderCount && position < adapterCount - mFooterCount && mOriginItemDecoration != null){
                mOriginItemDecoration.onDraw(c, parent, state);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        int adapterCount = parent.getAdapter().getItemCount() + mHeaderCount + mFooterCount;
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewPosition();
            if (position >= mHeaderCount && position < adapterCount - mFooterCount && mOriginItemDecoration != null) {
                mOriginItemDecoration.onDraw(c, parent, state);
            }
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int pos = params.getViewPosition();
        int adapterCount = parent.getAdapter().getItemCount() + mHeaderCount + mFooterCount;
        if(pos >= mHeaderCount && pos < adapterCount - mFooterCount){
            // noraml content
            if(null == mOriginItemDecoration){
                return;
            }
            mOriginItemDecoration.getItemOffsets(outRect, view, parent, state);
        }
    }

}

