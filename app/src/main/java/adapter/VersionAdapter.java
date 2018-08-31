package adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cobi.codingassignment.R;

import java.util.List;

import model.AndroidVersion;
import rest.RetroClient;

import com.cobi.codingassignment.databinding.ListItemBinding;

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
        ListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),rowLayout, parent, false);
        VersionViewHolder viewHolder = new VersionViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {

        AndroidVersion version = versions.get(position);
        Glide.with(context)
                .load(RetroClient.ROOT_URL + "/" +  version.getImage())
                .into(holder.itemBinding.image);
        holder.itemBinding.setVersion(version);
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return versions.size();
    }

    public AndroidVersion getAndroidVersionAt(int position) {
        return versions.get(position);
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding itemBinding;

        public VersionViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }
    }
}
