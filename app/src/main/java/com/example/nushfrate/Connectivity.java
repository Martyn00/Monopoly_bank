package com.example.nushfrate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Connectivity extends Fragment {
    private ConnectivityListener listener;
    Money buget = new Money(1500);
    public interface ConnectivityListener {
        void onInputConnectivitySent(Money input);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
        View v =  inflater.inflate(R.layout.connectivity, container, false);
        Toast.makeText(getActivity(), buget.getUser() + buget.toString(), Toast.LENGTH_LONG).show();
        return v;
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
}

