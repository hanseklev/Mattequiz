package com.s341872;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameEndFragment extends Fragment {

    public GameEndFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_game_end, container, false);

        final TextView finalHeader = view.findViewById(R.id.txt_end_score);
        finalHeader.setText(GameActivity.getFinalScore());

        final ImageButton homeBtn = view.findViewById(R.id.button_home);
        homeBtn.setOnClickListener(v-> getActivity().finish());

        return view;
    }
}