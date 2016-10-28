package com.cobi.codingassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.VersionAdapter;
import app.Versions;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.AndroidVersion;
import rest.ApiService;
import rest.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.DividerItemDecoration;

public class MainActivity extends AppCompatActivity {
    public static final String KEY = "version";
    public static final String TAG = MainActivity.class.getSimpleName();

    // views
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private View parentView;
    private List<AndroidVersion> androidVersions = new ArrayList<AndroidVersion>();
    private VersionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind views
        ButterKnife.bind(this);
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
        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();
        //Calling JSON
        Call<Versions> call = api.getVersionsJSON();
        //Enqueue Callback will be call when get response
        call.enqueue(new Callback<Versions>() {
            @Override
            public void onResponse(Call<Versions> call, Response<Versions> response) {
                //Dismiss dialog
                dialog.dismiss();
                Log.d(TAG, response.toString());
                if(response.isSuccessful()){
                    androidVersions = response.body().getVersions();
                    //Binding the List to Adapter
                    adapter = new VersionAdapter(androidVersions, R.layout.list_item, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(MainActivity.this, R.string.cannot_load_data, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Versions> call, Throwable t) {
                dialog.dismiss();
                Log.d("Error", t.getMessage());
                Toast.makeText(MainActivity.this, getString(R.string.no_internet_connection),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
