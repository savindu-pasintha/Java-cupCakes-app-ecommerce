package com.example.lola;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Operation extends SQLiteOpenHelper {
    public ArrayList<PModel> productArrayListModel = new ArrayList<PModel>();
    public ArrayList<CartModel> cartArrayListModel = new ArrayList<CartModel>();
    public ArrayList<userModel> userArrayListModel = new ArrayList<userModel>();
    public DB_Operation(@Nullable Context context) {
        super(context, "new_lola", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String  sql ="CREATE TABLE productTable(CODE TEXT PRIMARY KEY,NAME TEXT,PRICE TEXT,CATEGORY TEXT, DESCRIPTION Text, IMG Text)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE cartTabel(CODE TEXT PRIMARY KEY,NAME TEXT,PRICE TEXT,CATEGORY TEXT,QUANTITY TEXT,TOTAL TEXT,IMG Text)";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE registrationTable(USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE orderTable(ID INT PRIMARY KEY,CODE TEXT ,NAME TEXT,PRICE TEXT,CATEGORY TEXT, DESCRIPTION TEXT,QUENTITY TEXT,TOTAL TEXT, IMG Text)";
        sqLiteDatabase.execSQL(sql);

        /*
        String  sql ="CREATE TABLE productTable(CODE TEXT PRIMARY KEY,NAME TEXT,PRICE TEXT,CATEGORY TEXT, DESCRIPTION VARCHAR, IMG BLOB)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE cartTabel(CODE TEXT PRIMARY KEY,NAME TEXT,PRICE TEXT,CATEGORY TEXT,QUANTITY TEXT,TOTAL TEXT,IMG BLOB)";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE registrationTable(USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE orderTable(ID INT PRIMARY KEY AUTOINCREMENT,CODE TEXT ,NAME TEXT,PRICE TEXT,CATEGORY TEXT, DESCRIPTION TEXT,QUENTITY TEXT,TOTAL TEXT, IMG BLOB)";
        sqLiteDatabase.execSQL(sql);
         */
/*
        String  sql ="CREATE TABLE productTable(CODE TEXT(10) PRIMARY KEY,NAME TEXT(50),PRICE TEXT(10),CATEGORY TEXT(25), DESCRIPTION VARCHAR(150), IMG BLOB)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE cartTabel(CODE TEXT(10) PRIMARY KEY,NAME TEXT(50),PRICE TEXT(10),CATEGORY TEXT(25),QUANTITY TEXT(30),TOTAL TEXT(10),IMG BLOB)";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE registrationTable(USERNAME TEXT(50) PRIMARY KEY,PASSWORD TEXT(15))";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE orderTable(ID INT PRIMARY KEY AUTOINCREMENT,CODE TEXT(10) ,NAME TEXT(50),PRICE TEXT(10),CATEGORY TEXT(25), DESCRIPTION TEXT(150),QUENTITY TEXT(30),TOTAL TEXT(10), IMG BLOB)";
        sqLiteDatabase.execSQL(sql);

 */
/*
        String  sql ="CREATE TABLE productTable(CODE VARCHAR(10) PRIMARY KEY,NAME VARCHAR(50),PRICE VARCHAR(10),CATEGORY VARCHAR(25), DESCRIPTION VARCHAR(150), IMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE cartTabel(CODE VARCHAR(10) PRIMARY KEY,NAME VARCHAR(50),PRICE VARCHAR(10),CATEGORY VARCHAR(25),QUANTITY VARCHAR(30),TOTAL VARCHAR(10),IMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE registrationTable(USERNAME VARCHAR(50) PRIMARY KEY,PASSWORD VARCHAR(15))";
        sqLiteDatabase.execSQL(sql);
        sql ="CREATE TABLE orderTable(ID INT PRIMARY KEY AUTOINCREMENT,CODE VARCHAR(10) ,NAME VARCHAR(50),PRICE VARCHAR(10),CATEGORY VARCHAR(25), DESCRIPTION VARCHAR(150),QUENTITY VARCHAR(30),TOTAL VARCHAR(10), IMG BLOG)";
        sqLiteDatabase.execSQL(sql);


 */

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

      String  sql = "DROP TABLE IF EXISTS productTable";
        sqLiteDatabase.execSQL(sql);
        sql = "DROP TABLE IF EXISTS cartTabel";
        sqLiteDatabase.execSQL(sql);
        sql ="DROP TABLE IF EXISTS registrationTable";
        sqLiteDatabase.execSQL(sql);
        sql ="DROP TABLE IF EXISTS orderTable";
        sqLiteDatabase.execSQL(sql);


    }
    public long insert_product(PModel p){
          /*
        int x=0;
        if(productArrayListModel.add(p)){
            x=5;
        }

        return x;
           */

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CODE",p.getPrdId());
        values.put("NAME",p.getpName());
        values.put("PRICE",p.getpPrice());
        values.put("CATEGORY",p.getpCategory());
        values.put("DESCRIPTION",p.getDesc());
        values.put("IMG",p.getImgString());
       // values.put("IMG",p.getImg());
        return db.insert("productTable",null,values);

    }
    public int delete_Product(PModel b){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("productTable","CODE="+b.getPrdId(),null);
    }
    public int delete_productAllValues(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("productTable",null,null);
    }
    public long update_product(PModel k){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CODE",k.getPrdId());
        values.put("NAME",k.getpName());
        values.put("PRICE",k.getpPrice());
        values.put("CATEGORY",k.getpCategory());
        values.put("DESCRIPTION",k.getDesc());
        values.put("IMG",k.getImgString());
      //  values.put("IMG",k.getImg());
        return db.update("productTable",values,"CODE="+k.getPrdId(),null);
    }

    public ArrayList<PModel> viewAllProducts(){

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM productTable";
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        ArrayList<PModel> productList = new ArrayList<PModel>();
        if(count!=0){
            while (cursor.moveToNext()) {
                PModel obj = new PModel();
                //product.setPrdId(cursor.getInt(cursor.getColumnIndex("pID")))
                obj.setPrdId(cursor.getString(0));
                obj.setpName(cursor.getString(1));
                obj.setpPrice(cursor.getString(2));
                obj.setpCategory(cursor.getString(3));
                obj.setDesc(cursor.getString(4));
                obj.setImgString(cursor.getString(5));
                //obj.setImg(cursor.getBlob(5));
                productList.add(obj);
                Log.i("code---",obj.getPrdId().toString());
            }
        }

        return productList;
        /*
        return productArrayListModel;
         */
    }

/*------------------CART-----------------*/
//8-Insert Query Execute method insertCart Item
public long insert_Cart(CartModel c){
    SQLiteDatabase obj = getWritableDatabase();
    //option 1 execute update command
    ContentValues values = new ContentValues();
    values.put("CODE",c.getCode());
    values.put("NAME",c.getName());
    values.put("PRICE",c.getPrice());
    values.put("CATEGORY",c.getCategiry());
    values.put("QUANTITY",c.getQuentity());
    values.put("TOTAL",c.getTotal());
    values.put("IMG",c.getImageString());
   // values.put("IMG",c.getImgId());
   // values.put("IMG",c.getImgbyte());
    return obj.insert("cartTabel",null,values);

}
    public long update_Cart(CartModel c) {
        SQLiteDatabase obj = getWritableDatabase();
        //option 1 execute insert command
        ContentValues values = new ContentValues();
        values.put("CODE", c.getCode());
        values.put("NAME", c.getName());
        values.put("PRICE", c.getPrice());
        values.put("CATEGORY", c.getCategiry());
        values.put("QUANTITY", c.getQuentity());
        values.put("TOTAL", c.getTotal());
        values.put("IMG", c.getImageString());
        // values.put("IMG",c.getImgId());
        // values.put("IMG",c.getImgbyte());
        return obj.insert("cartTabel", null, values);//return the integer number 0 or 1
    }

    public ArrayList<CartModel> viewAllCartItems(){

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM cartTabel";
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        ArrayList<CartModel> cartList = new ArrayList<CartModel>();
        if(count!=0){
            while (cursor.moveToNext()) {
                CartModel obj = new CartModel();
                //product.setPrdId(cursor.getInt(cursor.getColumnIndex("pID")))
                obj.setCode(cursor.getString(0));
                obj.setName(cursor.getString(1));
                obj.setPrice(cursor.getString(2));
                obj.setCategiry(cursor.getString(3));
                obj.setQuentity(cursor.getString(4));
                obj.setTotal(cursor.getString(5));
                obj.setImageString(cursor.getString(6));
               // obj.setImgbyte(cursor.getBlob(6));
                cartList.add(obj);
            }
        }
        return cartList;


    }
    public int delete_cart(CartModel d){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("cartTabel","CODE="+d.getCode(),null);


        /*
        int x=0;
        if(cartArrayListModel.add(d)){
            x=5;
        }
        return x;

         */
    }
    public int delete_cartAllValues(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("cartTabel",null,null);
    }
    /*----------------------------------*/
    //long
    public long insertUserRegistration(String username,String password){

            SQLiteDatabase obj = getWritableDatabase();
            //option 1 execute insert command
            ContentValues values = new ContentValues();
            values.put("USERNAME",username);
            values.put("PASSWORD",password);
           return obj.insert("registrationTable",null,values);//return the integer number 0 or 1

        /*
        int x=0;
        userModel c=new userModel();
        c.setName(username);c.setPass(password);
        if(userArrayListModel.add(c)){
            x=5;
        }
        return x;
*/
    }
    public ArrayList<userModel> findUser(userModel userM){
        ArrayList<userModel> usersmList = new ArrayList<userModel>();
        usersmList = viewAllUsers();

        userModel usersm=new userModel();
        for(int i=0; i<usersmList.size(); i++){
            String a=usersmList.get(i).getName();
            String b=usersmList.get(i).getPass();
            if((userM.getName() == a )  && (userM.getPass() == b)){
                usersm.setName(a);
                usersm.setPass(b);
                usersmList.add(usersm);
            }
        }

        return usersmList;
        /*
        String sql = "SELECT * FROM registrationTable WHERE USERNAME='+userM.getName()+'";
         //CREATE TABLE registrationTable(USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)";

        SQLiteDatabase obj = getReadableDatabase();
        userModel usersm=new userModel();
        ArrayList<userModel> usersmList = new ArrayList<userModel>();
        Cursor cursor = obj.rawQuery(sql,null);
        int count = cursor.getCount();
        if(count!=0){
            cursor.moveToFirst();
            usersm.setName(cursor.getString(0));
            usersm.setPass(cursor.getString(1));
            usersmList.add(usersm);
        }
       */

    }
    public ArrayList<userModel> viewAllUsers(){

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM registrationTable";
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        ArrayList<userModel> userList = new ArrayList<userModel>();
        if(count!=0){
            int x=0;
            while (cursor.moveToNext()) {
                userModel obj = new userModel();
                obj.setName(cursor.getString(0));
                obj.setPass(cursor.getString(1));
                obj.setId(x);
                userList.add(obj);
                x++;
            }
        }
        return userList;


    }

    public int delete_user(userModel d){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("registrationTable","CODE="+d.getName(),null);
    }
}
