package com.levent_j.meizhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.bean.Gallery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levent_j on 16-4-18.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.mViewHolder>{

    private Context context;
    private List<Gallery> galleryList;
    private final LayoutInflater layoutInflater;
    private static final String IMAGE_URL = "http://tnfs.tngou.net/image";
    private int flag;

    public GalleryAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        galleryList = new ArrayList<>();
        flag = 0;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_gallery, null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Gallery gallery = galleryList.get(position);
        String url = gallery.getImg();
        Picasso.with(context)
                .load(IMAGE_URL+url)
                .resize(460,660)
                .centerCrop()
                .into(holder.mGalleryImg);
//        holder.mGalleryTitle.setText(gallery.getTitle());
    }


    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public void updateGalleryAdapter(ArrayList<Gallery> galleryList){
        this.galleryList.clear();
        this.galleryList.addAll(galleryList);
        notifyDataSetChanged();
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_gallery_img)
        ImageView mGalleryImg;
//        @Bind(R.id.tv_gallery_title)
//        TextView mGalleryTitle;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
