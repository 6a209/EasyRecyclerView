package com.easyrecyclerview.demo;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.li6a209.easyrecyclerview.EasyRecyclerView;


public class MainActivity extends AppCompatActivity {

    DemoAdapter mDemoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] datas = new String[50];
        for(int i = 0; i < 50; i++){
            datas[i] = "data " + i;
        }
        mDemoAdapter = new DemoAdapter(datas);
        initLinearLayout();

    }

    void initLinearLayout(){
        setTitle("LinearLayout");
        mDemoAdapter.setType(DemoAdapter.LINEAR_TYPE);
        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(32);
        header1.setGravity(Gravity.CENTER);
        header1.setBackgroundColor(getResources().getColor(R.color.light_blue));
        header1.setTextColor(getResources().getColor(android.R.color.white));
        header1.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView header2 = new TextView(this);
        header2.setText("this is header 2");
        header2.setTextSize(20);
        header2.setGravity(Gravity.CENTER);
        header2.setTextColor(getResources().getColor(android.R.color.white));
        header2.setBackgroundColor(getResources().getColor(R.color.light_blue));
        header2.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView footer1 = new TextView(this);
        footer1.setText("this is footer 1");
        footer1.setTextSize(20);
        footer1.setGravity(Gravity.CENTER);
        footer1.setBackgroundColor(getResources().getColor(R.color.green));
        footer1.setTextColor(getResources().getColor(android.R.color.white));
        footer1.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        recyclerView.addHeader(header1);
        recyclerView.addHeader(header2);
        recyclerView.addFooter(footer1);
        recyclerView.setHeaderDividerHeight(20);
        recyclerView.setAdapter(mDemoAdapter);
        setContentView(recyclerView);
    }

    void initStaggeredGrid(){

        setTitle("StaggeredGrid");
        mDemoAdapter.setType(DemoAdapter.STAGGERED_TYPE);
        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(20);
        header1.setGravity(Gravity.CENTER);
        header1.setTextColor(getResources().getColor(android.R.color.white));
        header1.setBackgroundColor(getResources().getColor(R.color.light_blue));
        recyclerView.addHeader(header1);

        TextView footer1 = new TextView(this);
        footer1.setText("this is footer 1");
        footer1.setTextSize(20);
        footer1.setGravity(Gravity.CENTER);
        footer1.setBackgroundColor(getResources().getColor(R.color.green));
        footer1.setTextColor(getResources().getColor(android.R.color.white));
        recyclerView.addFooter(footer1);

        recyclerView.setHeaderDividerHeight(30);
        recyclerView.addItemDecoration(new StaggeredItemDecoration(5));

        recyclerView.setAdapter(mDemoAdapter);
        setContentView(recyclerView);
    }



    void initGrid(){

        setTitle("Grid");
        mDemoAdapter.setType(DemoAdapter.GRID);
        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(20);
        header1.setGravity(Gravity.CENTER);
        header1.setTextColor(getResources().getColor(android.R.color.white));
        header1.setBackgroundColor(getResources().getColor(R.color.light_blue));
        recyclerView.addHeader(header1);

        TextView footer1 = new TextView(this);
        footer1.setText("this is footer 1");
        footer1.setTextSize(20);
        footer1.setGravity(Gravity.CENTER);
        footer1.setBackgroundColor(getResources().getColor(R.color.green));
        footer1.setTextColor(getResources().getColor(android.R.color.white));
        recyclerView.addFooter(footer1);

        recyclerView.addItemDecoration(new GridItemDecoration(5));

        recyclerView.setAdapter(mDemoAdapter);
        setContentView(recyclerView);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.linearlayout) {
            initLinearLayout();
        }else if(id == R.id.staggeredgrid){
            initStaggeredGrid();
        }else if(id == R.id.grid){
            initGrid();
        }

        return super.onOptionsItemSelected(item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
            mTextView = (TextView)v.findViewById(R.id.text1);
        }
    }



    public static class DemoAdapter extends RecyclerView.Adapter<ViewHolder> {
        private String[] mDataset;
        int mType;

        static final int LINEAR_TYPE = 0x00;
        static final int STAGGERED_TYPE = 0x01;
        static final int GRID = 0x02;
        private int [] mHeight = new int[150];

        public DemoAdapter(String[] myDataset) {
            mDataset = myDataset;
            for(int i = 0; i < 150; i++){
                mHeight[i] = (int) (Math.random() * 200 + 150);
            }

        }

        public void setType(int type){
            mType = type;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.text, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mDataset[position]);
            holder.mTextView.setGravity(Gravity.CENTER);
            if(mType == STAGGERED_TYPE){
                holder.mTextView.setTextColor(Color.parseColor("#ffffff"));
                holder.mTextView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight[position]));
                holder.mView.setBackgroundColor(Color.parseColor("#ec407a"));
            }else if(mType == GRID){
                holder.mTextView.setTextColor(Color.parseColor("#ffffff"));
                holder.mView.setBackgroundColor(Color.parseColor("#78909c"));
            }

        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }

    public class GridItemDecoration extends RecyclerView.ItemDecoration {
        private int space;
        public GridItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;

            EasyRecyclerView easyRecyclerView = (EasyRecyclerView)parent;
            if((parent.getChildAdapterPosition(view) - easyRecyclerView.getHeaderCount())  % 3 == 2){
                outRect.right = space;
            }
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public StaggeredItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            EasyRecyclerView easyRecyclerView = (EasyRecyclerView)parent;
            int pos = easyRecyclerView.getChildAdapterPosition(view);


            if(pos < easyRecyclerView.getHeaderCount()){
                //header
                return;
            }

            if(pos >= (easyRecyclerView.getAdapter().getItemCount() + easyRecyclerView.getHeaderCount())){
                //footer
                return;
            }

            int index = ((StaggeredGridLayoutManager.LayoutParams)view.getLayoutParams()).getSpanIndex();

            outRect.left = space;
            if(index  % 3 == 2){
                outRect.right = space;
            }
            outRect.bottom = space;
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }


}
