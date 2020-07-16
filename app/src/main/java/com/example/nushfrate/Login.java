package com.example.nushfrate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Login extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private LoginListener loginListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login, null);

        builder.setView(view)
                .setTitle("Login")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = editTextUsername.getText().toString();
                        loginListener.applyText(username);

                    }
                });

        editTextUsername = view.findViewById(R.id.Username);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            loginListener = (LoginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"nu nu");
        }
    }

    public interface LoginListener{
        void applyText(String username);
    }

}
