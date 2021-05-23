package com.example.lola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowProduct extends AppCompatActivity {

    Button back_btn,plus_btn,negative_btn;
    TextView quentity_lbl,total_lbl,price_lbl,name_lbl,description_lbl,category_lbl;
    ImageView imageView;
    String imageStringb;
    byte[] byteArray;
    String code;
    DB_Operation dbo;
    PModel objP;
    int indexArraylist;
    ArrayList<PModel> productListj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        //
        dbo=new DB_Operation(this);
        /*----------variable define------------*/
        back_btn=(Button)findViewById(R.id.back_btn3);
        negative_btn=(Button)findViewById(R.id.negative_btn);
        plus_btn=(Button)findViewById(R.id.plus_btn);
        quentity_lbl=(TextView)findViewById(R.id.quentity_lbl);
        total_lbl=(TextView)findViewById(R.id.total_lbl);
        price_lbl=(TextView)findViewById(R.id.price_lbl);
        name_lbl=(TextView)findViewById(R.id.name_lbl);
        description_lbl=(TextView)findViewById(R.id.description_lbl);
        category_lbl=(TextView)findViewById(R.id.category_lbl);
        imageView=(ImageView) findViewById(R.id.imageView3);

     /*-----------PRODUCT MANAGER CLASS USING SEND VALUE ACCESS(INTENT PUTEXTRA() and that values set to lables--*/
          try{
          Intent objIntent=getIntent();
                  Bundle objBundle=objIntent.getExtras();
                  //code,name,price,description,image
                     indexArraylist=objBundle.getInt("index");
                     code=objBundle.getString("code");
                     name_lbl.setText(objBundle.getString("name"));
                     price_lbl.setText(objBundle.getString("price"));
                     description_lbl.setText(objBundle.getString("description"));
                     category_lbl.setText(objBundle.getString("category"));

                 /*
                    imageStringb=objBundle.getString("imageData");
                    byteArray=objBundle.getByteArray("imageData");
                 */

                  /*   //byte array decode to image base64 string
                     byte byteArrayImage[]= Base64.decode( imageStringb,Base64.DEFAULT);
                     Bitmap bitmapObj = BitmapFactory.decodeByteArray( byteArrayImage,0, byteArrayImage.length);
                     imageView.setImageBitmap(bitmapObj);
                  */

              /*image get using databse*/
           //   ArrayList<PModel> productListj = new ArrayList<PModel>();
              productListj=dbo.viewAllProducts();
              if(productListj.size()>0){
                  byte arrim[]= Base64.decode(productListj.get(indexArraylist).getImgString(),Base64.DEFAULT);
                  //binary to image convert
                  Bitmap bitmapImage = BitmapFactory.decodeByteArray(arrim,0,arrim.length);
                  imageView.setImageBitmap(bitmapImage);
              }

          }catch(Exception e){
               Log.e("error in ShowProduct",e.getMessage().toString());
          }

    }

    public void goBack(View view){
        Intent obj = new Intent(ShowProduct.this,ProductManage.class);
        startActivity(obj);
    }
    public void plus(View view){
       try{
           String q=quentity_lbl.getText().toString();
           double p=Double.parseDouble(price_lbl.getText().toString());

           int i=0;
           i=Integer.parseInt(q);
           if(i>=0){
               i=i+1;
              quentity_lbl.setText(String.valueOf(i));
              double a=Double.valueOf(i)*p; //45.54
              total_lbl.setText(Double.toString(a));
        }else{
            quentity_lbl.setText("0");
        }

       }catch(Exception e){
           Log.e("plus",e.getMessage());
       }
    }

    public void negative(View view){
        try{
           String q=quentity_lbl.getText().toString();
            double p=Double.parseDouble(price_lbl.getText().toString());
            int i=0;
            i=Integer.parseInt(q);
       if(i>0 && i!=0){
            i=i-1;
       quentity_lbl.setText(String.valueOf(i));
       double a=Double.valueOf(i)*p;
       total_lbl.setText(Double.toString(a));
        }else{
            quentity_lbl.setText("0");
        }
    }catch(Exception e){
        Log.e("negative()",e.getMessage());
    }
    }

    //-add to cart 2-auto redirect again product page
    public void cartBtn(View view){
        try{
            //2
            Intent obj = new Intent(ShowProduct.this,Cart.class);
            startActivity(obj);
        }catch(Exception e){
            Log.e("negative()",e.getMessage());
        }
    }

    //-add to cart 2-auto redirect again product page
    public void buyBtn(View view){
       // Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_LONG).show();

        // String c=.getText().toString();
            String n=name_lbl.getText().toString();
            String ca="category1";
            String p=price_lbl.getText().toString();
            String d=description_lbl.getText().toString();
            String q=quentity_lbl.getText().toString();
            String t=total_lbl.getText().toString();
            String c=code;
            CartModel ct =new CartModel();
            ct.setCategiry(ca);ct.setName(n);ct.setPrice(p);ct.setQuentity(q);ct.setImgbyte(byteArray);
            ct.setTotal(t);ct.setCode(c);ct.setImageString(productListj.get(indexArraylist).getImgString());
            int x =Integer.valueOf(q);
                if(!q.isEmpty() && (x != 0))
                {
                    //Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_LONG).show();

                    try{
                        dbo=new DB_Operation(this);
                    if (dbo.insert_Cart(ct) > 0) {
                    Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_LONG).show();
                    //then go to again producrt page

                    Intent obj = new Intent(ShowProduct.this,Cart.class);
                    startActivity(obj);
                    }
                    else {
                        if (dbo.update_Cart(ct) > 0) {
                            Toast.makeText(getApplicationContext(), "Updated Cart Items.", Toast.LENGTH_LONG).show();
                            //then go to again producrt page
                            Intent obj = new Intent(ShowProduct.this,Cart.class);
                            startActivity(obj);
                        }else {
                            Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_LONG).show();
                        }
                     }

                      }catch (Exception e){
                Log.e("Product Insert",e.getMessage());
                      }

                }else {
                    Toast.makeText(getApplicationContext(), "Failed ! NO Values", Toast.LENGTH_LONG).show();
                }
    }

    public void alertMethod(String title,String msg){
        try{
            //1-builder to a set alert values
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(msg);
            //alertDialogBuilder.setMessage("Login successfully.");
            //2- execute alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }catch(Exception e){
            Log.e("error alertMethod()",e.getMessage().toString());
        }



    }


}