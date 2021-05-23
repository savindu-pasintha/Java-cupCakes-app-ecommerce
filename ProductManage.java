package com.example.lola;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;


public class ProductManage extends AppCompatActivity {
    Button back_button,see_btn,add_btn,update_btn,delete_btn,all_btn;
    EditText code_txt,price_txt,description_txt,name_txt,category_txt;
    ImageView image_view;
    //byte imageByte[];
    byte[] imageByte;
    DB_Operation db;
    String sixString;
    /*----Database----*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage);
        /*----------------------*/
        back_button=(Button)findViewById(R.id.back_button);
        see_btn=(Button)findViewById(R.id.see_btn);
        add_btn=(Button)findViewById(R.id.add_btn);
        delete_btn=(Button)findViewById(R.id.delete_btn);
        update_btn=(Button)findViewById(R.id.update_btn);
        /*----------------------*/
        code_txt=(EditText)findViewById(R.id.code_txt);
        price_txt=(EditText)findViewById(R.id.price_txt);
        description_txt=(EditText)findViewById(R.id.description_txt);
        name_txt=(EditText)findViewById(R.id.name_txt);
        category_txt=(EditText)findViewById(R.id.category_text);
        /*---------------------*/
        image_view=(ImageView)findViewById(R.id.image_view);
        //when open create the screen open the new database obj

        db=new DB_Operation(this);
    }

    public void goBack(View view){
        Intent obj = new Intent(ProductManage.this,ShowProduct.class);
        startActivity(obj);
    }
public void userManage(View view){
    Intent obj = new Intent(ProductManage.this,MainActivity.class);
    startActivity(obj);
}
    public void seeAll(View view){
        Intent obj = new Intent(ProductManage.this,Products.class);
        startActivity(obj);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seeView(View view){
          //data send to another page XML VIEW

     //   alertMethod("Product Insert Successfully.",String.valueOf( sixString.length()));

        //sixString=Base64.getEncoder().encodeToString(imageByte);

        if(name_txt.getText().toString().length()!=0) {

            Intent obj = new Intent(ProductManage.this, ShowProduct.class);
            obj.putExtra("code", code_txt.getText().toString());
            obj.putExtra("name", name_txt.getText().toString());
            obj.putExtra("price", price_txt.getText().toString());
            obj.putExtra("description", description_txt.getText().toString());
          //  obj.putExtra("imageData", sixString);
            // obj.putExtra("image",sixString.toString());//byte array pass
            startActivity(obj);

        }
       // }
            //alertMethod("work ");



      //  0775277928
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

    /*----select image ---*/
    public void select_image(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",0);
        intent.putExtra("aspectY",0);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data","true");
        startActivityForResult(Intent.createChooser(intent,"SELECT IMAGE"),111);
    }
    /*-----Selected image convert to byte[] view in ImageView-----*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111){
            if( data!= null){
                Uri uri = data.getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0, arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    image_view.setImageBitmap(bitmap);//front end view image

                }catch(IOException e){
                    Log.e("Error image save : ", e.getMessage());
                }
            }
        }
    }

    /*----------product add-----*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addProduct(View view){

        String c=code_txt.getText().toString();
        String n=name_txt.getText().toString();
        String ca=category_txt.getText().toString();
        String p=price_txt.getText().toString();
        String d=description_txt.getText().toString();
        Log.e("out",c+n+ca+p+d);
        byte im[]=imageByte;

        PModel productModelObj =new PModel();
      productModelObj.setPrdId(c);
      productModelObj.setpName(n);
      productModelObj.setpPrice(p);
      productModelObj.setDesc(d);
      productModelObj.setpCategory(ca);
      productModelObj.setImg(imageByte);

      //String sixString
        sixString=Base64.getEncoder().encodeToString(imageByte);
      productModelObj.setImgString(sixString);

      /*
      * image byte[]  arart to String base64 convert
      *1
      byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
      String encodedString = Base64.getEncoder().encodeToString(fileContent);
      * 2 encode
            byte[] imageBytes
            BASE64Encoder encoder = new BASE64Encoder();
           String imageString = encoder.encode(imageBytes);
      *2 decode to byte array
      *
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
      * */

        try {
            if(!c.isEmpty() && !n.isEmpty() && !d.isEmpty() && !p.isEmpty() && !ca.isEmpty() && !imageByte.toString().isEmpty())
            {   if (db.insert_product(productModelObj) > 0) {
                   //encode to base 64
                    Log.e("Base 64",sixString);
                    alertMethod("Product Insert Successfully.","");
                    clear();
                } else {
                    alertMethod("Insert Failed", "");
                }
            } else {
                alertMethod("Please Values Enter !", "");
            }
     }catch (Exception e){
        Log.e("Product Insert",e.getMessage());
      }
    }

    public void updateProduct(View view) {
        String c=code_txt.getText().toString();
        String n=name_txt.getText().toString();
        String ca=category_txt.getText().toString();
        String p=price_txt.getText().toString();
        String d=description_txt.getText().toString();
        Log.e("out",c+n+ca+p+d);
        byte im[]=imageByte;
        if(!c.isEmpty() && !n.isEmpty() && !d.isEmpty() && !p.isEmpty() && !ca.isEmpty() && !imageByte.toString().isEmpty())
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to Update ?");
            alertDialogBuilder.setMessage("Product code - "+c+"\n"+" Name - "+n+"\n"+" Price-"+p+"\n"+" Description-"+d+"\n"+" Category-"+ca+"\n"+" Image-"+im.toString() );
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
        /*--------------------------------------------*/
        PModel productModelObj =new PModel();
        productModelObj.setPrdId(c);
        productModelObj.setpName(n);
        productModelObj.setpPrice(p);
        productModelObj.setDesc(d);
        productModelObj.setpCategory(ca);
        productModelObj.setImg(imageByte);
        try {
              if (db.update_product(productModelObj) > 0) {
                alertMethod("Product Update Successfully.", "");
                clear();
            } else {
                alertMethod("Update Failed", "");
            }
        }catch (Exception e){
            Log.e("Product Update",e.getMessage());
        }
        /*-----------------------*/
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
        } else {
            alertMethod("Please Values Enter !", "");
        }
    }

    public void deleteProductAll(View view){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to Delete All Products ?");
        alertDialogBuilder.setMessage("");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (db.delete_productAllValues() > 0) {
                        alertMethod("All Products Deleted Successfully !", "");
                    } else {
                        alertMethod("Failed !", "");
                    }
                }catch (Exception e){
                    Log.e("delete err",e.getMessage());
                }
                /*------------------------------*/
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
    public void deleteProduct(View view) {
        if (!code_txt.getText().toString().isEmpty()) {
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to Delete ?");
        alertDialogBuilder.setMessage("Product code - "+code_txt.getText());
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                   /*-------------------------------------*/
                    PModel obj = new PModel();
                    obj.setPrdId(code_txt.getText().toString());
                    // alertMethod("Delete Successfully !", obj.getPrdId());
try {
    if (db.delete_Product(obj) > 0) {
        alertMethod("Delete Successfully !", "");
    } else {
        alertMethod("Failed !", "");
    }
}catch (Exception e){
    Log.e("delete err",e.getMessage());
}
                    /*------------------------------*/
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
    }else {
            alertMethod("Please Enter ProductID or Code !", "");
        }
    }
    public void clear() {
            code_txt.setText("");
            name_txt.setText("");
            price_txt.setText("");
            description_txt.setText("");
        }
    }

