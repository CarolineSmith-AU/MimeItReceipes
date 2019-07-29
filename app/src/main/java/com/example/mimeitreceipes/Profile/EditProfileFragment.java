package com.example.mimeitreceipes.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mimeitreceipes.Image.UniversalImageLoader;
import com.example.mimeitreceipes.R;

public class EditProfileFragment extends Fragment {

    private String TAG = "EditProfileFragment";
    private ImageView backArrowImageView;
    private ImageView profileImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit_profile, container, false);

        /* << -------------------- set up backArrowImageView to go back to ProfileActivity -------------------- >> */
        backArrowImageView = view.findViewById(R.id.backArrowImageView);
        backArrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:Navigating to ProfileActivity");
                getActivity().finish();
            }
        });



        profileImageView = view.findViewById(R.id.profileCircleImageView);

        setProfileImage();

        return view;
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage:started");
        String imageUrl = "https://storage.googleapis.com/gweb-uniblog-publish-prod/images/android_ambassador_v1_cmyk_200px.max-200x200.png";
        UniversalImageLoader.setImage(imageUrl, profileImageView, null, "");

    }
}
