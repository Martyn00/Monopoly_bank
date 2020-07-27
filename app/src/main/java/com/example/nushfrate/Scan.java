package com.example.nushfrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static java.lang.Long.parseLong;

public class Scan extends Fragment implements View.OnClickListener{
    private Scanlistener listener;
    public interface Scanlistener{
        void onInputScanSent(Money input);
    }
    Button Scan;
    String Scanned;
    Money buget = new Money(1500);
    @Nullable
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
        View v =  inflater.inflate(R.layout.scan, container, false);
        Scan = (Button) v.findViewById(R.id.button);
        Scan.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            IntentIntegrator integrator = new IntentIntegrator(this.getActivity());
            integrator.setPrompt("Scan a QRcode");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.forSupportFragment(this).initiateScan();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Scanned = result.getContents();
                String[] words = Scanned.split(" ");
                String bani = words[1];
                buget.cresteBani(parseLong(words[1]));
                listener.onInputScanSent(buget);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void updateBani(Money value){
        buget.setSum(value.getSum());
        buget.setUser(value.getUser());
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Scan.Scanlistener){
            listener = (Scan.Scanlistener) context;
        }else{

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
