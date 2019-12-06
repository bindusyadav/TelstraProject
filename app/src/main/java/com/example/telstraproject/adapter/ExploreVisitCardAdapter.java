package com.example.telstraproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.telstraproject.R;
import com.example.telstraproject.utils.ExploreVisit;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreVisitCardAdapter extends RecyclerView.Adapter<ExploreVisitCardAdapter.CardViewHolder> {

    private Context mContext;
    private ArrayList<ExploreVisit> mExploreVisit;

    public ExploreVisitCardAdapter(Context context, ArrayList<ExploreVisit> exploreVisits){
        mContext=context;
        mExploreVisit=exploreVisits;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_listdetails,parent,false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ExploreVisit  currentVisitItem = mExploreVisit.get(position);

        String imageUrl = currentVisitItem.getmImageUrl();
        String subTitle = currentVisitItem.getmSubTitle();
        String description = currentVisitItem.getmDescription();

        if (subTitle != ""){
            holder.mSubtitle_textView.setText(subTitle);
        }

        if (description != ""){
            holder.mDescription_textView.setText(description);
        }

        Log.d("SAMATA", "imageUrl"+imageUrl);
        if (imageUrl != ""){
            Picasso.get().load(imageUrl).placeholder(R.mipmap.ic_launcher).into(holder.mImageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("SAMATA", "onSuccess ");
                }

                @Override
                public void onError(Exception e) {
                    Log.d("SAMATA", "onError "+ e);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return mExploreVisit.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mSubtitle_textView;
        public TextView mDescription_textView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mSubtitle_textView = itemView.findViewById(R.id.subTitle);
            mDescription_textView = itemView.findViewById(R.id.description);

        }
    }
}
