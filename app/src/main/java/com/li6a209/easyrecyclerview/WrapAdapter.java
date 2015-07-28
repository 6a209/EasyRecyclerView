package com.li6a209.easyrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 6a209 on 15/7/27.
 */
class WrapAdapter extends RecyclerView.Adapter {

    private RecyclerView.Adapter mAdapter;
    List<View> mHeaderViews;
    List<View> mFooterViews;

    // use type to spec header or footer
    static final int HEADER_VIEW_BASE = 0xf00000;
    static final int FOOTER_VIEW_BASE = 0x0f0000;

    private static class HeaderFooterViewHolder extends RecyclerView.ViewHolder{
        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    public WrapAdapter(List<View> headers, List<View> footer, RecyclerView.Adapter adapter){
        mAdapter = adapter;
        mHeaderViews = headers;
        mFooterViews = footer;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        if((type & 0xff0000) == 0xf00000){
            return new HeaderFooterViewHolder(mHeaderViews.get(type & 0x00ffff));
        }else if((type & 0xff0000) == 0x0f0000){
            return new HeaderFooterViewHolder(mFooterViews.get(type & 0x00ffff));
        }else{
            return mAdapter.onCreateViewHolder(viewGroup, type);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        if(pos >= getHeadersCount() && pos < getHeadersCount() + mAdapter.getItemCount()){
            mAdapter.onBindViewHolder(viewHolder, pos - getHeadersCount());
        }
    }

    @Override
    public int getItemCount() {
        if(null != mAdapter){
            return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
        }else{
            return getHeadersCount() + getFootersCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int headerCount = getHeadersCount();
        if(position < headerCount){
            return HEADER_VIEW_BASE + position;
        }else if(position > headerCount && position < headerCount + mAdapter.getItemCount()){
            return mAdapter.getItemViewType(position - headerCount);
        }else {
            return FOOTER_VIEW_BASE + (position - headerCount - mAdapter.getItemCount());
        }
    }

    private int getHeadersCount(){
        return mHeaderViews.size();
    }

    private int getFootersCount(){
        return mFooterViews.size();
    }
}
