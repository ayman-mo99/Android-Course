package com.example.myapplication;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public  class ViewHolder extends RecyclerView.ViewHolder {
    TextView user_name;
    TextView phone;
    CheckBox ph;
    View vv;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        user_name = itemView.findViewById(R.id.TV_user_name);
        phone = itemView.findViewById(R.id.TV_phone_number);
        ph = itemView.findViewById(R.id.ph);
        vv = itemView.findViewById(R.id.vvv);
    }

}
