package com.li6a209.easyrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 6a209 on 14/11/11.
 *
 * support HeaderView & FooterView
 *
 * support LinearLayoutManager | GridLayoutManager orientation vertical && StaggeredGridLayoutManager
 */
public class EasyRecyclerView extends RecyclerView{

    private List<View> mHeaderViewInfos = new ArrayList<View>();
    private List<View> mFooterViewInfos = new ArrayList<View>();

    WrapAdapter mWrapAdapter;
    DividerDecoration mHeaderFooterDecoration;

    int mType = -1;

    Adapter mOriginAdapter;


    public EasyRecyclerView(Context context){
        this(context, null);
    }

    public EasyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.easyrecycler);
        Drawable headerDrawable = typedArray.getDrawable(R.styleable.easyrecycler_header_divider);
        Drawable footerDrawable = typedArray.getDrawable(R.styleable.easyrecycler_footer_divider);
        int headerHeight = typedArray.getDimensionPixelSize(R.styleable.easyrecycler_header_divider_height, 0);
        int footerHeight = typedArray.getDimensionPixelSize(R.styleable.easyrecycler_footer_divider_height, 0);
        mHeaderFooterDecoration = new DividerDecoration();
        if(null != headerDrawable){
            mHeaderFooterDecoration.setHeaderDrawable(headerDrawable);
        }
        if(null != footerDrawable){
            mHeaderFooterDecoration.setFooterDrawable(footerDrawable);
        }

        if(headerHeight > 0){
            mHeaderFooterDecoration.setHeaderDividerHeight(headerHeight);
        }

        if(footerHeight > 0){
            mHeaderFooterDecoration.setFooterDividerHeight(footerHeight);
        }
        typedArray.recycle();
        super.addItemDecoration(mHeaderFooterDecoration);
    }


    @Override
    public void setLayoutManager(LayoutManager layoutManager){
        // need wrap ?
        super.setLayoutManager(layoutManager);
        if(layoutManager instanceof StaggeredGridLayoutManager){
            mType = WrapAdapter.STAGGEREDGRI;
        }else if(layoutManager instanceof GridLayoutManager){
            mType = WrapAdapter.GRID;

        }else if(layoutManager instanceof LinearLayoutManager){
            mType = WrapAdapter.LINEARLAYOUT;
        }
    }


    @Override
    public void setAdapter(Adapter adapter){
        mOriginAdapter = adapter;
        mWrapAdapter = new WrapAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        mWrapAdapter.setLayoutType(mType);
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            GridLayoutManager.SpanSizeLookup originSpanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            WrapGridSpanSizeLookup wrapGridSpanSizeLookup = new WrapGridSpanSizeLookup(originSpanSizeLookup);
            wrapGridSpanSizeLookup.updateSpanCount(gridLayoutManager.getSpanCount());
            wrapGridSpanSizeLookup.updateHeaderCount(mHeaderViewInfos.size());
            wrapGridSpanSizeLookup.updateFooterCount(mFooterViewInfos.size());
            wrapGridSpanSizeLookup.updateAdapter(mWrapAdapter);
            gridLayoutManager.setSpanSizeLookup(wrapGridSpanSizeLookup);

        }
        super.setAdapter(mWrapAdapter);
    }

    @Override
    public Adapter getAdapter(){
       return mOriginAdapter;
    }

    @Override
    public void addItemDecoration(ItemDecoration decoration){
        super.addItemDecoration(new WrapItemDecoration(decoration, mHeaderViewInfos.size(), mFooterViewInfos.size()));
    }

    public void addHeader(View view){
        mHeaderViewInfos.add(view);
        mHeaderFooterDecoration.setHeaderCount(mHeaderViewInfos.size());
    }

    public void addFooter(View view){
        mFooterViewInfos.add(view);
        mHeaderFooterDecoration.setFooterCount(mFooterViewInfos.size());
    }

    public void removeHeaderView(View view){
        mHeaderViewInfos.remove(view);
    }

    public void removeFooterView(View view){
        mFooterViewInfos.remove(view);
    }


    public void setHeaderDrawable(Drawable drawable){
        mHeaderFooterDecoration.setHeaderDrawable(drawable);
    }

    public void setFooterDrawable(Drawable drawable){
        mHeaderFooterDecoration.setFooterDrawable(drawable);
    }

    public void setHeaderDividerHeight(int height){
        mHeaderFooterDecoration.setHeaderDividerHeight(height);
    }

    public void setFooterDividerHeight(int height){
        mHeaderFooterDecoration.setFooterDividerHeight(height);
    }

    public int getHeaderCount(){
        return mHeaderViewInfos.size();
    }

    public int getFooterCount(){
        return mFooterViewInfos.size();
    }

    static class WrapGridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{
        int mHeaderCount;
        int mFooterCount;
        int mSpanCount;

        Adapter mAdapter;

        GridLayoutManager.SpanSizeLookup mOriginSpanSizeLookup;

        public WrapGridSpanSizeLookup(GridLayoutManager.SpanSizeLookup originSpanSizeLookup){
            mOriginSpanSizeLookup = originSpanSizeLookup;
        }

        void setOriginSpanSizeLookup(GridLayoutManager.SpanSizeLookup lookup){
            GridLayoutManager.SpanSizeLookup spanSizeLookup = mOriginSpanSizeLookup;
        }


        void updateHeaderCount(int headerCount){
            mHeaderCount = headerCount;
        }

        void updateFooterCount(int footerCount){
            mFooterCount = footerCount;
        }

        void updateSpanCount(int spanCount){
            mSpanCount = spanCount;
        }

        void updateAdapter(Adapter adapter){
            mAdapter = adapter;
        }


        @Override
        public int getSpanSize(int position) {
            if(position < mHeaderCount){
                return mSpanCount;
            }else if(position >= mHeaderCount && position < mAdapter.getItemCount() - mFooterCount){
                return mOriginSpanSizeLookup.getSpanSize(position);
            }else{
                return mSpanCount;
            }

        }
    }

}
