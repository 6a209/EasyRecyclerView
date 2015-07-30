package com.easyrecyclerview.demo;

import android.graphics.Color;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    void initLinearLayout(){
        String [] datas = new String[50];
        for(int i = 0; i < 50; i++){
            datas[i] = "it is test data " + i;
        }
        DemoAdapter adapter = new DemoAdapter(datas);

        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(20);
        header1.setGravity(Gravity.CENTER);
        header1.setBackgroundColor(R.color.material_blue_grey_800);
        header1.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView header2 = new TextView(this);
        header2.setText("this is header 2");
        header2.setTextSize(20);
        header2.setGravity(Gravity.CENTER);
        header2.setBackgroundColor(Color.parseColor("#acacac"));

        TextView footer1 = new TextView(this);
        footer1.setText("this is footer 1");
        footer1.setTextSize(20);
        footer1.setGravity(Gravity.CENTER);
        footer1.setBackgroundColor(R.color.material_blue_grey_800);
        footer1.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        recyclerView.addHeader(header1);
        recyclerView.addHeader(header2);
        recyclerView.addFooter(footer1);
        recyclerView.setHeaderDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.setHeaderDividerHeight(63);
        recyclerView.setAdapter(adapter);
        setContentView(recyclerView);
    }

    void initStaggeredGrid(){
        String [] datas = new String[150];
        for(int i = 0; i < 150; i++){
            datas[i] = "it is test data " + i;
        }
        DemoAdapter adapter = new DemoAdapter(datas);
        adapter.setType(DemoAdapter.STAGGERED_TYPE);
        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(20);
        header1.setGravity(Gravity.CENTER);
        header1.setBackgroundColor(R.color.material_blue_grey_800);
        recyclerView.addHeader(header1);

        recyclerView.setAdapter(adapter);
        setContentView(recyclerView);
    }



    void initGird(){
        String [] datas = new String[150];
        for(int i = 0; i < 150; i++){
            datas[i] = "it is test data " + i;
        }
        DemoAdapter adapter = new DemoAdapter(datas);
        adapter.setType(DemoAdapter.GRID);
        EasyRecyclerView recyclerView = new EasyRecyclerView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


        TextView header1 = new TextView(this);
        header1.setText("this is header 1");
        header1.setTextSize(20);
        header1.setGravity(Gravity.CENTER);
        header1.setBackgroundColor(R.color.material_blue_grey_800);
        recyclerView.addHeader(header1);

        recyclerView.setAdapter(adapter);
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
            initGird();
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
                mHeight[i] = (int) (Math.random() * 100 + 50);
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
                holder.mTextView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeight[position]));
                holder.mView.setBackgroundColor(Color.parseColor("#992222"));
            }

        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
