package com.s341872;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ConfirmationDialogFragment extends DialogFragment {
    Dialog dialog;
    OnClickListener listener;
    private final String dialogText;

    public interface OnClickListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    public ConfirmationDialogFragment(String dialogText) {
        this.dialogText = dialogText;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_cancel_game_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv = dialog.findViewById(R.id.dialog_fragment_text);
        tv.setText(dialogText);


        Button positiveButton = dialog.findViewById(R.id.btn_dialog_positive);
        Button negativeButton = dialog.findViewById(R.id.btn_dialog_negative);
        positiveButton.setOnClickListener(view -> listener.onDialogPositiveClick(this));
        negativeButton.setOnClickListener(view -> listener.onDialogNegativeClick(this));

        dialog.create();

        return dialog;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(requireActivity().toString() + " must implement CancelGameDialogListener");
        }
    }
}
