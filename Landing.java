package com.example.lola;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //waiting time
        try {
            //progerss loading
            final ProgressDialog pd = new ProgressDialog(Landing.this);
            pd.setTitle("Loading..");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.show();

            //asyncronous method ekk time out set kenn
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd.dismiss();
                    //goto new page
                    Intent objc = new Intent(Landing.this,Login.class);
                    startActivity(objc);
                }
            },9000);

        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
//waiting time
    }
}