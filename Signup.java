package com.example.lola;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    //define ui componets id names as a variable
    EditText usernamevalue,passwordvalue,comfrimpasswordvalue;
    TextView lbl_error,one,two,three;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //input feilds id values access
        usernamevalue=(EditText)findViewById(R.id.username_reg);
        passwordvalue=(EditText)findViewById(R.id.password_reg);
        comfrimpasswordvalue=(EditText)findViewById(R.id.comfrimpassword_reg);
        btnSignUp=(Button) findViewById(R.id.signup_btn);
        lbl_error=(TextView)findViewById(R.id.lbl_error);

        one=(TextView)findViewById(R.id.one);
        two=(TextView)findViewById(R.id.two);
        three=(TextView)findViewById(R.id.three);

        //text watcher
        usernamevalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!usernamevalue.getText().toString().isEmpty() && !passwordvalue.getText().toString().isEmpty()){
                    btnSignUp.setEnabled(true);
                }else{
                    btnSignUp.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!usernamevalue.getText().toString().isEmpty() && !passwordvalue.getText().toString().isEmpty()){
                    btnSignUp.setEnabled(true);
                }else{
                    btnSignUp.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        comfrimpasswordvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!usernamevalue.getText().toString().isEmpty() && !comfrimpasswordvalue.getText().toString().isEmpty()){
                    btnSignUp.setEnabled(true);
                }else{
                    btnSignUp.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void signUp_btn_click(View view){

        String user = usernamevalue.getText().toString();
        String pass = passwordvalue.getText().toString();
        String comfrim = comfrimpasswordvalue.getText().toString();

        try{
         // one.setText(user);
         // two.setText(pass);
         // three.setText(comfrim);
            //progerss loading
            final ProgressDialog pd = new ProgressDialog(Signup.this);
            pd.setTitle("SignUp");
            pd.setMessage("Authenticating...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.show();
            //asyncronous method ekk time out set kenn
            new android.os.Handler().postDelayed(new Runnable() {@Override public void run() { pd.dismiss();
                if( !user.isEmpty() && pass.equals(comfrim) && (pass.length()==8)){
                    saveUserRegistrations(user,pass);//saved db
                    gotologin(view);
                    clear();//clearthe text feils
                }else{
                    lbl_error.setText("password length should be 8 or not match !");
                }
            }},2000);


        }catch(Exception e) {
            Log.i("error in Login",e.getMessage());
            clear();//clearthe text feilds
        }
    }

    public void gotologin(View view){
        Intent obj = new Intent(Signup.this,Login.class);
        startActivity(obj);
    }

    public void alertMethod(String msg){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(msg);
        //alertDialogBuilder.setMessage("Login successfully.");

        //2- execute alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void clear(){
        usernamevalue.setText(null);
        passwordvalue.setText(null);
        comfrimpasswordvalue.setText(null);
        lbl_error.setText(null);

    }

    @SuppressLint("LongLogTag")
    public void saveUserRegistrations(String username,String password){
            DB_Operation db=new DB_Operation(this);
            try {
                if (db.insertUserRegistration(username, password) > 0) {
                    alertMethod("Registration Successfully Completed.");
                } else {
                    alertMethod("Registration Failed !.");
                }
            }catch(Exception e){
                Log.e("SignUp", e.getMessage());
            }
    }


}