package com.mobileexaminationchecker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class email_verify_dialog extends AppCompatDialogFragment {

    Button ok_btn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_email_verify,null);
        builder.setView(view);
        ok_btn = view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), MainActivity.class)));
        setCancelable(false);
        return builder.create();
    }

}
