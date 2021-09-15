package com.s341872;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.Objects;

public class GameEndDialogFragment extends DialogFragment {

    private GameEndDialogListener listener;

    public interface GameEndDialogListener {
        public void onEndClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        String header = getResources().getString(R.string.game_end_header);
        builder.setMessage(header + " " + GameActivity.getFinalScore())
                .setPositiveButton(R.string.dialog_home, (dialogInterface, i) ->
                        listener.onEndClick(GameEndDialogFragment.this));
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (GameEndDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString() + " must implement GameEndDialogListener");
        }
    }
}