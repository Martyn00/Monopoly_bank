package com.example.nushfrate;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.prefs.BackingStoreException;

public class Pay extends Fragment {
    Money buget;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
         View v = inflater.inflate(R.layout.pay, container, false);
        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap("Mergeeeeeee", BarcodeFormat.QR_CODE, 400, 400);
            ImageView img_qr = (ImageView) v.findViewById(R.id.imageView);
            img_qr.setImageBitmap(bitmap);
        } catch (Exception e){

        }

        return v;
    }

}
