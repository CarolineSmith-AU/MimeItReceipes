package com.example.mimeitreceipes.Profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mimeitreceipes.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Class responsible for adapting item_profile_post.xml into a RecyclerView.
 */
public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;

    public ProfileRecyclerViewAdapter(Context contextIn, List<Post> postListIn) {
        context = contextIn;
        postList = postListIn;
    }

    /**
     * Method Responsible for creating the RecyclerView view using LayoutInflater.
     * @param viewGroup - ViewGroup
     * @param i
     * @return - returns the View inflated by LayoutInflater.
     */
    @NonNull
    @Override
    public ProfileRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile_post, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * Method Responsible for binding data from EditTexts to the view.
     * @param viewHolder
     * @param i - represents index of a Post in postList.
     */
    @Override
    public void onBindViewHolder(@NonNull final ProfileRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Post post = postList.get(i);

        //TODO: Figure out how to get image using FireBase
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(post.getAppend() + post.getImageUrl(), viewHolder.dishImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (viewHolder.profileProgressBar != null) {
                    viewHolder.profileProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (viewHolder.profileProgressBar != null) {
                    viewHolder.profileProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (viewHolder.profileProgressBar != null) {
                    viewHolder.profileProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (viewHolder.profileProgressBar != null) {
                    viewHolder.profileProgressBar.setVisibility(View.GONE);
                }
            }
        });

        viewHolder.dishImageView.setImageResource(R.drawable.ic_food); //Figure out Image/ImageView issue later
        viewHolder.recipeTitleTextView.setText(post.getRestaurant() + ": " + post.getDish());
        viewHolder.descriptionTextView.setText(post.getDescription());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    /**
     * Nested class that holds all necessary widgets of corresponding layout.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView dishImageView;
        TextView recipeTitleTextView;
        TextView descriptionTextView;
        ProgressBar profileProgressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishImageView = itemView.findViewById(R.id.dishImageView);
            recipeTitleTextView = itemView.findViewById(R.id.recipeTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            profileProgressBar = itemView.findViewById(R.id.profilePhotoProgressBar);
        }
    }
}
