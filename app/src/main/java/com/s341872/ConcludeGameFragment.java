package com.s341872;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import java.util.Objects;

public class ConcludeGameFragment extends DialogFragment {

    public interface ConcludeGameListener {
        void onDialogPositiveClick(DialogFragment dialog);

        //void onDialogNegativeClick(DialogFragment dialog);
    }

    ConcludeGameFragment listener;
/**
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(R.string.dialog_cancel_game)
                .setPositiveButton(R.string.dialog_yes, (dialogInterface, i) ->
                        listener.onDialogPositiveClick(ConcludeGameFragment.this));
        return builder.create();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
            .setMessage("hello")
            .setPositiveButton(getString(R.string.ok), (dialog, which) -> {} )
            .create();
    }
*/

//    ConcludeGameListener listener;


}
