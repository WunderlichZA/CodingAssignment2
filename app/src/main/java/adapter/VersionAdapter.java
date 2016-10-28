package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cobi.codingassignment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.AndroidVersion;
import rest.RetroClient;

/**
 * Created by student13 on 10/26/2016.
 */

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionViewHolder>{

    private List<AndroidVersion> versions;
    private int rowLayout;
    private Context context;

    public VersionAdapter(List<AndroidVersion> versions, int rowLayout, Context context) {
        this.versions = versions;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {

        AndroidVersion version = versions.get(position);
        holder.mName.setText(version.getName());
        //holder.mImage.setImageResource(Integer.parseInt(versions.get(position).getImage()));
        Glide.with(context)
                .load(RetroClient.ROOT_URL + "/" +  version.getImage())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return versions.size();
    }

    public AndroidVersion getAndroidVersionAt(int position) {
        return versions.get(position);
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image) ImageView mImage;
        @BindView(R.id.name) TextView mName;

        public VersionViewHolder(View v) {
            super(v);
            // bind views
            ButterKnife.bind(this, v);
        }
    }
}
