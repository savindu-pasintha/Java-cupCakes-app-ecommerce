package com.example.lola;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    GridView gridVariable;
    // Search EditText
    EditText inputSearch;
    //
    Button logOutbtn;
    //
    ProductGrideViewAdapter adapter;
    //
    ArrayList<PModel> ProductModelArrayList;  //= new ArrayList<PModel>();

    // RecyclerView user interface component
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private RecyclerView.Adapter<MovieViewHolder> adapterRecycle;

    // LayoutManager for the RecyclerView
    private RecyclerView.LayoutManager layoutManager;
    /*---------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        gridVariable=(GridView)findViewById(R.id.grid);
        //view grid
        logOutbtn=(Button)findViewById(R.id.logout_btn);
        DB_Operation db = new DB_Operation(this);

        /*---------Recycle view-----------------------------------------------------------------------*/
        // Find the UI components
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // Arrange the items linearly
       // this.layoutManager = new LinearLayoutManager(this);//vertical recycle
        this.layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        // Get the movies
        List<Movie> movies = getMovies();

        // Create the MovieAdapter
        this.adapterRecycle = new MovieAdapter(movies);

        // Set the LayoutManager
        this.recyclerView.setLayoutManager(layoutManager);

        // Set the Adapter
        this.recyclerView.setAdapter(adapterRecycle);
/*---------------end of the recycle view data load-----------------*/


        try{
            /**
             *   ProductModelArrayList = db.viewAllProducts();
             *             if(ProductModelArrayList.size()>0){
             */
            ProductModelArrayList = new ArrayList<PModel>();
            ProductModelArrayList = db.viewAllProducts();
            if(ProductModelArrayList.size()>0){
                //ProductGrideViewAdapter adapter = new ProductGrideViewAdapter(this,ProductModelArrayList);
                adapter = new ProductGrideViewAdapter(this, ProductModelArrayList);
                gridVariable.setAdapter(adapter);
            }else{
                Toast.makeText(this,"No Products",Toast.LENGTH_LONG).show();
                Log.e("Erro :" , "no products");
            }
        }catch (Exception e){
            Log.e("Erro :" , e.getMessage());
        }

          /*
        //search function
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence searchWord, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Products.this.adapter.getFilter().filter(searchWord);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        */


    }


//recycler category
private List<Movie> getMovies() {
    // Create a List of movies
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie("$ 3", "for Birthday...",R.drawable.cup1));
    movies.add(new Movie("$ 2.5","for Anivasary...",R.drawable.cup2));
    movies.add(new Movie("$ 1", "for Your New Baby...",R.drawable.cup3));
    movies.add(new Movie("$ 5", "for Valantine...",R.drawable.cup4));
    movies.add(new Movie("$ 4", "for Graguation...",R.drawable.cup5));
    movies.add(new Movie("$ 2.8", "for Motherday...",R.drawable.cup6));
    movies.add(new Movie("$ 4", "for Gift...",R.drawable.cup7));
    movies.add(new Movie("$ 6", "for Anivasary...",R.drawable.cup8));
    movies.add(new Movie("$ 3", "for Valantine...",R.drawable.cup11));
    movies.add(new Movie("$ 9", "for Birthday..",R.drawable.cup10));
    movies.add(new Movie("$ 8", "for Your New Baby...",R.drawable.cup12));

    return movies;
}

    public void goCart(View view){
        Intent obj = new Intent(Products.this,Cart.class);
        startActivity(obj);
    }

    public void logoutbtn(View view){
        alertMethod("Do you want to LogOut ?");
    }

    //image click event
    public void viewClickedImage(View view) {
        //selected index value get in click the image button this value pass by adapter btutton.setTag(position);
        int i = (int) view.getTag();

       // Toast.makeText(getApplicationContext(),ProductModelArrayList.get(i).getImg().toString(), Toast.LENGTH_LONG).show();
try {
    String a = ProductModelArrayList.get(i).getPrdId();
    Intent obji = new Intent(Products.this,ShowProduct.class);

    obji.putExtra("index",i);
    obji.putExtra("code",ProductModelArrayList.get(i).getPrdId());
    obji.putExtra("name", ProductModelArrayList.get(i).getpName());
    obji.putExtra("price", ProductModelArrayList.get(i).getpPrice());
    obji.putExtra("description", ProductModelArrayList.get(i).getDesc());
    obji.putExtra("category", ProductModelArrayList.get(i).getpCategory());
    //obji.putExtra("image", ProductModelArrayList.get(i).getImgString());
    // obji.putExtra("image",ProductModelArrayList.get(i).getImg());


    startActivity(obji);
}catch (Exception e){
    Log.e("err",e.getMessage());
}
    }
    public void alertMethod(String msg){
        //1-builder to a set alert values
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(msg);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"LogOut.", Toast.LENGTH_SHORT).show();
                Intent obj = new Intent(Products.this, Login.class);
                startActivity(obj);
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