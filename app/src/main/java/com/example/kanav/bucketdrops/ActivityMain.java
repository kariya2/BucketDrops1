package com.example.kanav.bucketdrops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kanav.bucketdrops.adapters.AdapterDrops;
import com.example.kanav.bucketdrops.beans.Drop;
import com.example.kanav.bucketdrops.widgets.BucketRecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class ActivityMain extends AppCompatActivity  {

    private final String TAG = "KK";
    Toolbar mToolbar;
    Button buttonAdd;
    BucketRecyclerView mRecycler;
    Realm mRealm;
    RealmResults<Drop> mResults;
    AdapterDrops mAdapter;
    View mEmptyView;

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialogAdd();
        }
    };

    private RealmChangeListener mChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            Log.d(TAG, "onChange: was called");
            mAdapter.update(mResults);

        }
    };

    private void showDialogAdd() {
        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(),"Add");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEmptyView = findViewById(R.id.empty_drops);
        buttonAdd = (Button) findViewById(R.id.button_add);
        mRealm = Realm.getDefaultInstance();
        mResults = mRealm.where(Drop.class).findAllAsync();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler = (BucketRecyclerView) findViewById(R.id.rv_drops);
        mRecycler.hideIfEmpty(mToolbar);
        mRecycler.showIfEmpty(mEmptyView);
        mRecycler.setLayoutManager(manager);
        mAdapter = (new AdapterDrops(this,mResults));
        mRecycler.setAdapter(mAdapter);
        buttonAdd.setOnClickListener(btnListener);
        setSupportActionBar(mToolbar);
        initBackgroundImage();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mResults.addChangeListener(mChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListener(mChangeListener);
    }

    private void initBackgroundImage(){

        ImageView background = (ImageView) findViewById(R.id.iv_background);
        Glide.with(this).load(R.drawable.background).
                centerCrop().
                into(background);

    }

}

