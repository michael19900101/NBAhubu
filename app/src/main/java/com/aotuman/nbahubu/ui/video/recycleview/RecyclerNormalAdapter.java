package com.aotuman.nbahubu.ui.video.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aotuman.nbahubu.R;
import com.aotuman.nbahubu.model.video.VideoItemModel;
import com.aotuman.nbahubu.ui.video.VideoViewModel;

import java.util.List;


public class RecyclerNormalAdapter extends RecyclerView.Adapter {
    private final static String TAG = "RecyclerBaseAdapter";

    private List<VideoItemModel> itemDataList = null;
    private Context context = null;
    private VideoViewModel videoViewModel;

    public RecyclerNormalAdapter(Context context, List<VideoItemModel> itemDataList, VideoViewModel videoViewModel) {
        this.itemDataList = itemDataList;
        this.context = context;
        this.videoViewModel = videoViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_video_item_normal, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemNormalHolder(context, v, videoViewModel);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemNormalHolder recyclerItemViewHolder = (RecyclerItemNormalHolder) holder;
        recyclerItemViewHolder.setRecyclerBaseAdapter(this);
        recyclerItemViewHolder.onBind(position, itemDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public void setListData(List<VideoItemModel> data) {
        itemDataList = data;
        notifyDataSetChanged();
    }
}
