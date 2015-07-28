package com.li6a209.easyrecyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
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
    WrapItemDecoration mHeaderFooterDecoration;


    public EasyRecyclerView(Context context){
        this(context, null);
    }

    public EasyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mHeaderFooterDecoration = new WrapItemDecoration(null);
        super.addItemDecoration(mHeaderFooterDecoration);
    }


    @Override
    public void setAdapter(Adapter adapter){
        mWrapAdapter = new WrapAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        super.setAdapter(mWrapAdapter);
    }

    @Override
    public void addItemDecoration(ItemDecoration decoration){
        super.addItemDecoration(new WrapItemDecoration(decoration));
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

}
