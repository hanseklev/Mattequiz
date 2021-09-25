package com.s341872.ui.game;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.s341872.R;

public class GameEndDialogFragment extends DialogFragment {
    Dialog dialog;
    String finalScore;
    GameEndDialogListener listener;


    public interface GameEndDialogListener {
        void onHomeClick(DialogFragment dialog);
    }

    public GameEndDialogFragment(String finalScore){
        this.finalScore = finalScore;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_game_end_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView scoreText = dialog.findViewById(R.id.text_score);
        scoreText.setText(finalScore);

        ImageButton homeButton = dialog.findViewById(R.id.btn_home);
        homeButton.setOnClickListener(view -> listener.onHomeClick(this));
        dialog.create();

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (GameEndDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(requireActivity().toString() + " must implement GameEndDialogListener");
        }
    }
}
