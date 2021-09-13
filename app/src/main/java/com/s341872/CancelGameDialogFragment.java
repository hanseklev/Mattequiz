package com.s341872;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

public class CancelGameDialogFragment extends DialogFragment {
    Dialog dialog;

    public interface CancelGameDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    CancelGameDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
     /*   AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(R.string.dialog_cancel_game)
                .setPositiveButton(R.string.dialog_yes, (dialogInterface, i) ->
                        listener.onDialogPositiveClick(CancelGameDialogFragment.this))
                .setNegativeButton(R.string.dialog_no, (dialogInterface, i) ->
                        listener.onDialogNegativeClick(CancelGameDialogFragment.this)

                );
        return builder.create();*/
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_cancel_game_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button positiveButton = dialog.findViewById(R.id.btn_dialog_positive);
        Button negativeButton = dialog.findViewById(R.id.btn_dialog_negative);
        positiveButton.setOnClickListener(view -> listener.onDialogPositiveClick(this));
        negativeButton.setOnClickListener(view -> listener.onDialogNegativeClick(this));

        dialog.create();

        return dialog;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CancelGameDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(Objects.requireNonNull(getActivity()).toString() + " must implement CancelGameDialogListener");
        }
    }
}
