package com.s341872;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.Objects;

public class CancelGameDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(R.string.dialog_cancel_game)
                .setPositiveButton(R.string.dialog_yes, (dialogInterface, i) -> {


                }).setNegativeButton(R.string.dialog_no, (dialogInterface, i) -> {

        });
        return builder.create();
    }
}
