package com.example.lola;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartItemAdapter extends BaseAdapter {

    Context context;
    TextView name,category,price,quentity,total;
    ImageView imageView;
    Button removeItemBtn;

    ArrayList<CartModel> cartArrayList;

    //constructor method 1
    public CartItemAdapter(Context context, ArrayList<CartModel>  list){
        this.context = context;
        this. cartArrayList =  list;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //externam lay out access
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //mokakd view kal yutu lay out ek set kala
        View v = inflater.inflate(R.layout.sample_cart_items, parent, false);

        //external layout variable access and define
        name=(TextView)v.findViewById(R.id.name);
        category=(TextView)v.findViewById(R.id.category);
        price=(TextView)v.findViewById(R.id.price);
        quentity=(TextView)v.findViewById(R.id.quentity);
        total=(TextView)v.findViewById(R.id.total);
        imageView = (ImageView)v.findViewById(R.id.img);
        removeItemBtn=(Button)v.findViewById(R.id.removeItemBtn);

       // CartModel  cartModel=new CartModel();
        CartModel cartModel = cartArrayList.get(position);//arraylist index value

        //external lay out variables to values set in array inside value assign
        name.setText(cartModel.getName());
        category.setText(cartModel.getCategiry());
        price.setText(cartModel.getPrice());
        quentity.setText(cartModel.getQuentity());

        //Base 64 to byte array
        byte arr[]= Base64.decode(cartModel.getImageString(),Base64.DEFAULT);
        //binary to image convert
        Bitmap bitmap = BitmapFactory.decodeByteArray(arr,0,arr.length);
        imageView.setImageBitmap(bitmap);
       // imageView.setImageResource(cartModel.getImgId());

        //conversation
        double p= Double.valueOf(price.getText().toString());
        double q= Double.valueOf(quentity.getText().toString());

        total.setText(String.valueOf(p*q));

        //selected bton index value access and pass to the click event
        removeItemBtn.setTag(position);

        // Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImg(),0,product.getImg().length);
        // imageView.setImageBitmap(bitmap);
        return v;
    }


}
