package com.levent_j.meizhi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.activity.GalleryDetailActivity;
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

    public GalleryAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        galleryList = new ArrayList<>();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_gallery, null);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Gallery gallery = galleryList.get(position);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (position==0){
            lp.setMargins(0, 0, 0, 0);
        }else if (position==1){
            lp.setMargins(0,200,0,0);
        }else if (position>1){
            lp.setMargins(0,100,0,0);
        }
        holder.mGalleryImg.setLayoutParams(lp);

        String url = gallery.getImg();

        double w = getWidth();
        double h = w / 0.618;

        Picasso.with(context)
                .load(IMAGE_URL + url)
                .resize((int)w,(int)h)
                .centerCrop()
                .into(holder.mGalleryImg);
//        holder.mGalleryTitle.setText(gallery.getTitle());
    }

    private double getWidth() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth()/2-80;
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

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mGalleryImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = galleryList.get(getPosition()).getId();
                    Intent intent = new Intent(context, GalleryDetailActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
