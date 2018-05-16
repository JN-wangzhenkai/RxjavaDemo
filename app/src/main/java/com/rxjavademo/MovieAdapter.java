package com.rxjavademo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private List<MovieSubject> mList;
    private Context mContext;

    public MovieAdapter(List<MovieSubject> mSubList,Context context) {

        this.mList=mSubList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle,parent,false);

        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieSubject subject=mList.get(position);

        Glide.with(mContext)
                .load(subject.getSubjects().get(position).getImages().getLarge())
                .thumbnail(0.1f)
                .into(holder.imageView);
      holder.mTextTtle.setText(subject.getTitle());
      holder.mTextTime.setText(subject.getSubjects().get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return  mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView mTextTtle, mTextTime;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            mTextTtle = itemView.findViewById(R.id.title);
            mTextTime = itemView.findViewById(R.id.time);

        }
    }

}
