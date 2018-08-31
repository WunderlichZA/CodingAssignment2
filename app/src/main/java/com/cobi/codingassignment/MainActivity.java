package com.cobi.codingassignment;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.VersionAdapter;
import model.AndroidVersion;
import util.DividerItemDecoration;
import viewmodel.VersionsViewModel;

import com.cobi.codingassignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String KEY = "version";
    public static final String TAG = MainActivity.class.getSimpleName();

    // views
    Toolbar toolbar;
    RecyclerView recyclerView;

    private View parentView;
    private List<AndroidVersion> androidVersions = new ArrayList<>();
    private VersionAdapter adapter;

    VersionsViewModel versionsViewModel;

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        versionsViewModel = ViewModelProviders.of(this).get(VersionsViewModel.class);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerView.hasFixedSize();
        mainBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this));

        // Set Toolbar
        setSupportActionBar(toolbar);

        // initialize recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && mGestureDetector.onTouchEvent(e)){
                    int position = rv.getChildPosition(child);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(KEY, (Serializable) androidVersions.get(position));
                    startActivity(intent);
                    return false;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // download data
        makeHttpCall();
    }

    public void makeHttpCall(){
        // show a progress dialog while data is being fetched
        final ProgressDialog dialog;
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait");
        dialog.show();

        versionsViewModel.getListLiveData().observe(this, androidVersions ->{
            adapter = new VersionAdapter(androidVersions, R.layout.list_item, this);
            mainBinding.recyclerView.setAdapter(adapter);
            dialog.dismiss();
        });
    }
}