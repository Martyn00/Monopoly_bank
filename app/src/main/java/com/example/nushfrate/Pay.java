package com.example.nushfrate;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.prefs.BackingStoreException;

import static java.lang.Long.parseLong;

public class Pay extends Fragment implements View.OnClickListener {
    Money buget = new Money(1500);
    EditText text;
    Button Generator;
    ImageView img_qr;
    private Paylistener listener;
    public interface Paylistener{
        void onInputPaySent(Money input);
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
        View v = inflater.inflate(R.layout.pay, container, false);
        Generator = (Button) v.findViewById(R.id.Button);
        text = (EditText) v.findViewById(R.id.Lose);
        Generator.setOnClickListener(this);
        img_qr = (ImageView) v.findViewById(R.id.imageView);
        return v;
    }

    @Override
    public void onClick(View view) {
        String Bani = text.getText().toString();
        if( view.getId() == R.id.Button){
            buget.scadeBani(parseLong(Bani));
            listener.onInputPaySent(buget);
            String Parse = buget.getUser() + " " + Bani;
            try{
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(Parse, BarcodeFormat.QR_CODE, 400, 400);
                img_qr.setImageBitmap(bitmap);
            } catch (Exception e){

            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Pay.Paylistener){
            listener = (Pay.Paylistener) context;
        }else{

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public void updateBani(Money value){
       buget.setSum(value.getSum());
       buget.setUser(value.getUser());
    }
}
