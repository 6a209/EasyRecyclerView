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

    private Drawable mHeaderDrawable;
    private Drawable mFooterDrawable;

    private int mHeaderDividerHeight = 0;
    private int mFooterDividerHeight = 0;

    public WrapItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        mOriginItemDecoration = itemDecoration;
    }

    public void setHeaderCount(int count){
        mHeaderCount = count;
    }

    public void setFooterCount(int count){
        mFooterCount = count;
    }

    public void setHeaderDrawable(Drawable drawable){
        mHeaderDrawable = drawable;
    }

    public void setFooterDrawable(Drawable drawable){
        mFooterDrawable = drawable;
    }

    public void setHeaderDividerHeight(int height){
        mHeaderDividerHeight = height;
    }

    public void setFooterDividerHeight(int height){
        mFooterDividerHeight = height;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        int adapterCount = parent.getAdapter().getItemCount();
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
        int adapterCount = parent.getAdapter().getItemCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewPosition();
            if (position >= mHeaderCount && position < mFooterCount && mOriginItemDecoration != null) {
                mOriginItemDecoration.onDraw(c, parent, state);
            }else{

                if(position < mHeaderCount){
                    if(null != mHeaderDrawable){
                        int left = parent.getPaddingLeft();
                        int top = child.getBottom() + params.bottomMargin;
                        int right =  parent.getWidth() - parent.getPaddingRight();
                        int bottom = top + mHeaderDrawable.getIntrinsicHeight();
                        mHeaderDrawable.setBounds(left, top, right, bottom);
                        mHeaderDrawable.draw(c);
                    }
                }else if(position >= adapterCount - mFooterCount && position != (adapterCount - 1)){

                    if(null != mFooterDrawable){

                        int left = parent.getPaddingLeft();
                        int top = child.getBottom() + params.bottomMargin;
                        int right =  parent.getWidth() - parent.getPaddingRight();
                        int bottom = top + mFooterDrawable.getIntrinsicHeight();
                        mFooterDrawable.setBounds(left, top, right, bottom);
                        mFooterDrawable.draw(c);
                    }
                }
            }
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int pos = params.getViewPosition();
        int adapterCount = parent.getAdapter().getItemCount();
        if(pos < mHeaderCount){
            // header
            outRect.set(0, 0, 0, mHeaderDividerHeight);
        }else if(pos >= mHeaderCount && pos <= adapterCount - mFooterCount){
            // noraml content
            if(null == mOriginItemDecoration){
                return;
            }
            mOriginItemDecoration.getItemOffsets(outRect, view, parent, state);
        }else {
            // footer
            if(pos != (adapterCount -1)){
                outRect.set(0, 0, 0, mFooterDividerHeight);
            }
        }
    }

}

