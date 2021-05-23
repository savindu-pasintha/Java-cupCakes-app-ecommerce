package com.example.lola;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductGrideViewAdapter extends ArrayAdapter<PModel> {

    public ProductGrideViewAdapter(@NonNull Context context, ArrayList<PModel> ProductPModelArrayList) {
        super(context,0, ProductPModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.sample_product_card_item_layout, parent, false);
        }

        PModel pModel = getItem(position);
        TextView textVariable = listitemView.findViewById(R.id.textVariable);
        ImageView imageVariable = listitemView.findViewById(R.id.imageVariable);
        TextView priceText=listitemView.findViewById(R.id.priceVariable);
        TextView categoryVariable=listitemView.findViewById(R.id.categoryVariable);
        TextView idtext=listitemView.findViewById(R.id.idtext);

        textVariable.setText(pModel.getpName());

        //insert drawable folder image
        //imageVariable.setImageResource(pModel.getImgId());




        //Base 64 to byte array
        byte arr[]= Base64.decode(pModel.getImgString(),Base64.DEFAULT);
        //binary to image convert
        Bitmap bitmap = BitmapFactory.decodeByteArray(arr,0,arr.length);
        imageVariable.setImageBitmap(bitmap);



        priceText.setText(pModel.getpPrice());
       // priceText.setText("$ 14");
        idtext.setText(pModel.getPrdId());
        categoryVariable.setText(pModel.getpCategory());
        //select krpu image ike index ek access krnn  // imageVariable.setImageResource(obj.getImgid());
        imageVariable.setTag(position);

        return listitemView;
    }


}

