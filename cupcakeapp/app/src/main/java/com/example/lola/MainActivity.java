package com.example.lola;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<userModel> usersf = new ArrayList<userModel>();
    EditText txtID, txtName,txtEmail, txtPass;
    Button add,backbtn;
    DB_Operation db=new DB_Operation(this);
    ListView userListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID =(EditText) findViewById(R.id.ID);
        txtName =(EditText) findViewById(R.id.NAME);
        txtEmail =(EditText) findViewById(R.id.EMAIL);
        txtPass =(EditText) findViewById(R.id.PASSWORD);
        backbtn=(Button)findViewById(R.id.backbtn);
        add=(Button)findViewById(R.id.add);


        userListView = (ListView)findViewById(R.id.list);

        sh();

    }
public void back(View view){
    Intent obj = new Intent(MainActivity.this,ProductManage.class);
    startActivity(obj);
}
    private void sh(){
/*
        for(int i=0; i<100;i++){
            userModel usera = new userModel();
            usera.setId(i);
            usera.setName("name");
            usera.setEmail("");
            usera.setPass("12345");
            usersf.add(usera);
        }

*/

        try{

            usersf = db.viewAllUsers();
            if(usersf.size()>0){
                UserAdapter productAdapter = new UserAdapter(this,usersf);
                userListView.setAdapter(productAdapter);
            }else{
                Toast.makeText(this,"No Products",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("Erro User View :" , e.getMessage());
        }
    }

    public void insert_user(View view){
        //Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        if(!txtName.getText().toString().isEmpty()){
            userModel userp = new userModel();
            userp.setName(txtName.getText().toString());
            userp.setPass(txtPass.getText().toString());
                if(db.insertUserRegistration(userp.getName(),userp.getPass())>0) {
                    Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                    sh();
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
        }


    }



    public void getUserItem(View view){

        int index = (int)view.getTag();

        String x=usersf.get(index).getName();

        if(!String.valueOf(x).isEmpty()) {
            userModel user = new userModel();
            user.setName(x);
            db  = new DB_Operation(this);
            if(db.delete_user(user)>0){
                Toast.makeText(this,"User record Deleted!",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        sh();

    }
}