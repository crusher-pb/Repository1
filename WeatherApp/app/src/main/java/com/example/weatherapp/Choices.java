package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Choices extends RecyclerView.Adapter<Choices.viewHolder>{
    @NonNull
    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mcontext;
    private OnNoteListener mOnNoteListener;

    public Choices(Context context,ArrayList<String> imageNames, ArrayList<String> images,OnNoteListener onNoteListener){
        mcontext=context;
        mImageNames=imageNames;
        this.mOnNoteListener=onNoteListener;
        mImages=images;
    }
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new viewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        Glide.with(mcontext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.icon);
        holder.imageName.setText(mImageNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView imageName;
        OnNoteListener onNoteListener;
        public viewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            icon=(ImageView) itemView.findViewById(R.id.img);
            imageName=(TextView) itemView.findViewById(R.id.text);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
