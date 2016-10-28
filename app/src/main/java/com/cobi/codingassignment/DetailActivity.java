package com.cobi.codingassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.AndroidVersion;
import rest.RetroClient;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    // views
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.api)
    TextView api;
    @BindView(R.id.released_date)
    TextView released;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Bind views
        ButterKnife.bind(this);
        // Set Toolbar
        setSupportActionBar(toolbar);
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
        AndroidVersion androidVersion = (AndroidVersion) getIntent()
                .getSerializableExtra(MainActivity.KEY);

        // check if version has a thumbnail
        if (!(androidVersion.getImage() == null)) {
            Glide.with(this)
                    .load(RetroClient.ROOT_URL + "/" +  androidVersion.getImage())
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(image);
        }
        // set version name
        name.setText("Name         :  " + androidVersion.getName());
        // set version number
        version.setText("Version      :  " + androidVersion.getVersion());
        // check if version has an api version
        if (!(androidVersion.getApi() == null)) {
            api.setText("Api              :  " + androidVersion.getApi());
        }
        // check if version has a release date
        if (!(androidVersion.getReleased() == null)) {
            released.setText("Released   :  " + androidVersion.getReleased());
        }
    }
}
