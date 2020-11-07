package com.example.Monopoly_bank;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;


public class Connectivity extends Fragment implements View.OnClickListener{
    private ConnectivityListener listener;
    public TextView userText;
    private Button userChange;
    private Application app;
    Money buget = new Money(1500);
    Connectivity(Application application){
    this.app = application;
    }

    public interface ConnectivityListener {
        void onInputConnectivitySent(Money input);
        void showLoginDialog(int x);
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
        View v =  inflater.inflate(R.layout.connectivity, container, false);
        Toast.makeText(getActivity(), buget.getUser() + buget.toString(), Toast.LENGTH_LONG).show();
        userText = v.findViewById(R.id.username);
        userChange = v.findViewById(R.id.changeUser);
        userChange.setOnClickListener(this);
        userText.setText(buget.getUser());

    return v;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeUser:
                login();
//                userText.setText(buget.getUser());
                System.out.println(buget.getUser());
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
        @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Connectivity.ConnectivityListener){
            listener = (Connectivity.ConnectivityListener) context;
        }else{

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void updateBani(Money newSum){
        buget.setSum(newSum.getSum());
        buget.setUser(newSum.getUser());
    }
    public void login(){
        listener.showLoginDialog(2);
    }
}
