package com.cobi.codingassignment;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import model.AndroidVersion;
import rest.RetroClient;

import com.cobi.codingassignment.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    ActivityDetailBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding  = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Set Toolbar
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(getIntent() != null) {
            // populate fields
            setData();
        }

    }

    /**
     * Populates and display selected object data
     */
    private void setData() {
//        AndroidVersion androidVersion = (AndroidVersion) getIntent()
//                .getSerializableExtra(MainActivity.KEY);
//
//        // check if version has a thumbnail
//        if (!(androidVersion.getImage() == null)) {
//            Glide.with(this)
//                    .load(RetroClient.ROOT_URL + "/" +  androidVersion.getImage())
//                    .placeholder(R.mipmap.placeholder)
//                    .error(R.mipmap.placeholder)
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .into(image);
//        }
//        // set version name
//        name.setText("Name         :  " + androidVersion.getName());
//        // set version number
//        version.setText("Version      :  " + androidVersion.getVersion());
//        // check if version has an api version
//        if (!(androidVersion.getApi() == null)) {
//            api.setText("Api              :  " + androidVersion.getApi());
//        }
//        // check if version has a release date
//        if (!(androidVersion.getReleased() == null)) {
//            released.setText("Released   :  " + androidVersion.getReleased());
//        }
    }
}
