package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Todos> items1 = new ArrayList<>();
    RecyclerView recyclerView;
    ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

        items1.add(new Todos("1", "description1", false));
        items1.add(new Todos("2", "description2", false));
        items1.add(new Todos("3", "description3", false));
        items1.add(new Todos("4", "description4", false));
        items1.add(new Todos("5", "description5", true));
        items1.add(new Todos("6", "description6", false));
        items1.add(new Todos("7", "description7", false));
        items1.add(new Todos("100", "description100", false));
        items1.add(new Todos("200", "description200", false));
        items1.add(new Todos("300", "description300", true));
        items1.add(new Todos("1000", "description1000", false));
        items1.add(new Todos("2000", "description2000", false));
        contactsAdapter = new ContactsAdapter(this, items1, new ContactsAdapter.OnClickListener() {

            @Override
            public void ondelete(final int index) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete this item ?");
                builder.setTitle("Delete item");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items1.remove(index);
                        contactsAdapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Log.d("hh", R.drawable.fenix + "");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(contactsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}

