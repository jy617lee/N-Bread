package com.yunihuani.accountmanager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunihuani.accountmanager.R;


public class MainActivityFriendFragment extends Fragment {
    public MainActivityFriendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_friend, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label_friend);
        dummyTextView.setText("FRIEND");
        return rootView;
    }
}
