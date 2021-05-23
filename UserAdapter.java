package com.example.lola;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {


    Context context;
    TextView Id,Name,Email,Password;

    Button btnGetItem;

    ArrayList<userModel> users;

    public UserAdapter(Context context, ArrayList<userModel> users){
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.user_layout, viewGroup, false);

        Id = (TextView) v.findViewById(R.id.id);
        Name = (TextView) v.findViewById(R.id.name);
        Email = (TextView) v.findViewById(R.id.email);
        Password = (TextView) v.findViewById(R.id.password);
        //delete btn
        btnGetItem = (Button) v.findViewById(R.id.delete);

        btnGetItem.setTag(i);

        userModel p = users.get(i);
        Id.setText(""+String.valueOf(p.getId()));
        Name.setText(p.getName());
        Email.setText(p.getEmail());
        Password.setText(p.getPass());

        return v;
    }
}
