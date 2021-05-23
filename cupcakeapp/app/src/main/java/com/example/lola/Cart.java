package com.example.lola;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    ListView listView;
    TextView cartNoOfItems,totalP;
    ArrayList<CartModel> cartArrayList = new ArrayList<CartModel>();
    DB_Operation db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//
        db=new DB_Operation(this);

        //1
        listView=(ListView)findViewById(R.id.cartList);
        cartNoOfItems=(TextView)findViewById(R.id.noitems);
        totalP=(TextView)findViewById(R.id.totalP);
        //
        cartshowdata();

    }

    private void cartshowdata(){
        db  = new DB_Operation(this);
        try{
            cartArrayList = db.viewAllCartItems();
            if( cartArrayList.size()>0){
                CartItemAdapter adapeter = new CartItemAdapter(this,cartArrayList);
                listView.setAdapter( adapeter);
                cartNoOfItems.setText(String.valueOf(cartArrayList.size()));
                //
        double p,q,t=0;
        for(int i=0; i<cartArrayList.size(); i++){
        CartModel cartModel = cartArrayList.get(i);//arraylist index value objext get and asign
        //conversation
        p=Double.valueOf(cartModel.getPrice().toString());
        q=Double.valueOf(cartModel.getQuentity().toString());
        t=t+(p*q);
        }
        totalP.setText(String.valueOf(t));
            }else{
                Toast.makeText(this,"No Cart Items !",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("Erro User View :" , e.getMessage());
        }
    }
    //delete all the cart item
    public void allCartItemClear(View view){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want Remove this All Cart items ?");
        alertDialogBuilder.setMessage("");
        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // alertMethod("Delete Successfully !", obj.getPrdId());
                if (db.delete_cartAllValues()>0) {
                    //  cartArrayList.remove(index);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfull Removed.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    Intent obj = new Intent(Cart.this,Products.class);
                    startActivity(obj);
                }else{
                    //  cartArrayList.remove(index);
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    Intent obj = new Intent(Cart.this,Products.class);
                    startActivity(obj);
                }

            }


        });
        alertDialogBuilder.setNeutralButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Canceled !", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        //2- execute alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //delete a cart item one
    public void clickedCartItemClear(View view){
        //selected index value get in click the button this value pass by adapter btutton.setTag(position);
        int index = (int)view.getTag();
        String p,q,n,c;
        p=cartArrayList.get(index).getPrice();
        c=cartArrayList.get(index).getCategiry();
        n=cartArrayList.get(index).getName();
        q=cartArrayList.get(index).getQuentity();
        String deletecode=cartArrayList.get(index).getCode();

        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want Remove this Cart item ?");
        alertDialogBuilder.setMessage("\n"+String.valueOf(index+1)+"\n"+n+"\n"+c+"\n"+p+"\n"+q+"\n");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*-------------------------------------*/
                CartModel objc = new CartModel();
                objc.setCode(deletecode);
                // alertMethod("Delete Successfully !", obj.getPrdId());
                if (db.delete_cart(objc)>0) {
                    //  cartArrayList.remove(index);
                    Toast toast = Toast.makeText(getApplicationContext(), "Removed.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                //relaod the cart list
                    cartshowdata();
                }else{
                    //  cartArrayList.remove(index);
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }


        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Canceled !", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        //2- execute alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //delete all the cart item
    public void payBtn(View view){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("PAYMENT IS COMPLETED .");
        alertDialogBuilder.setMessage("come again.. !");
        alertDialogBuilder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Canceled !", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        //2- execute alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //go back to view all the products
    public void goBack(View view){
        Intent obj = new Intent(Cart.this,Products.class);
        startActivity(obj);
    }

    public void alertMethod(String msg){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Remove All the Cart Items In the Cart.", Toast.LENGTH_SHORT).show();
                cartNoOfItems.setText("00");
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Canceled !", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        //2- execute alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}