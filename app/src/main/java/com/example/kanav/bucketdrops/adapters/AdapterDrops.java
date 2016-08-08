package com.example.kanav.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kanav.bucketdrops.R;
import com.example.kanav.bucketdrops.beans.Drop;

import io.realm.RealmResults;

/**
 * Created by Kanav on 6/25/16.
 */
public class AdapterDrops extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM = 0;
    public static final int FOOTER = 1;

    private LayoutInflater mInflater;
    public final  String TAG  = "KK";
    private RealmResults<Drop> mResults;


    public void update(RealmResults<Drop> results){
        mResults = results;
        notifyDataSetChanged();
    }

    public AdapterDrops(Context context, RealmResults<Drop> results){
        mInflater = LayoutInflater.from(context);
        update(results);
    }

    @Override
    public int getItemViewType(int position) {
        if (mResults == null || position< mResults.size()){
            return ITEM;
        }
        else{
            return FOOTER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            View view = mInflater.inflate(R.layout.footer,parent,false);
            FooterHolder holder = new FooterHolder(view);
            return holder;

        }
        else{
            View view = mInflater.inflate(R.layout.file_drop,parent,false);
            DropHolder holder = new DropHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DropHolder){
            DropHolder dropHolder = (DropHolder) holder;
            Drop drop = mResults.get(position);
            dropHolder.mTextWhat.setText(drop.getWhat());
            Log.d(TAG, "onBindViewHolder: " + position);
        }


    }

    @Override
    public int getItemCount() {
        return mResults.size() + 1;
    }

    public static class DropHolder extends RecyclerView.ViewHolder{

        TextView mTextWhat;
        public DropHolder(View itemView) {
            super(itemView);
            mTextWhat = (TextView) itemView.findViewById(R.id.tv_what);

        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder{

        Button mBtnAdd;
        public FooterHolder(View itemView) {
            super(itemView);
            mBtnAdd = (Button) itemView.findViewById(R.id.btn_footer);

        }
    }




}
