package com.example.Monopoly_bank;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
    public String username;
    public Connectivity connectivity;
    public int x;
    public Login(int x){
        this.x = x;
    }
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
                        username = editTextUsername.getText().toString();
                        if(x == 1){
                            loginListener.applyText(username);
                        }else{
                            loginListener.applyText1(username);
                        }

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
//            loginListener1 = (LoginListener1) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"nu nu");
        }
    }


    public interface LoginListener{
        void applyText(String username);
        void applyText1(String username);
    }

}
