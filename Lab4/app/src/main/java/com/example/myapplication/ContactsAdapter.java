package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context  context;
    ArrayList<Todos> items1;
    OnClickListener OnClickListener;

    public ContactsAdapter(Context context, ArrayList<Todos> items1,OnClickListener listener){
        this.context = context;
        this.items1=items1;
        this.OnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.contact_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.user_name.setText(items1.get(position).getTitle());
        holder.phone.setText(items1.get(position).getDis());
        holder.ph.setChecked(items1.get(position).isDone());
        holder.vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"shortclick",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("ph", holder.user_name.getText());
                intent.putExtra("ms",holder.phone.getText());
                intent.putExtra("po",holder.ph.isChecked());
                context.startActivity(intent);
            }
        });

        holder.vv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                OnClickListener.ondelete(position);
                Toast.makeText(context,"Loooooongclick",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public interface OnClickListener{
        void ondelete(int index);
    }

}
