package com.furkankurt.dogbreeds.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.furkankurt.dogbreeds.Activity.DisplayScreen;
import com.furkankurt.dogbreeds.Model.DogBreedsModel;
import com.furkankurt.dogbreeds.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<DogBreedsModel> message;
    Activity activity;

    public RecyclerViewAdapter(List<DogBreedsModel> message, Activity activity) {
        this.message = message;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textName.setText(String.valueOf(message.get(position)));
            holder.itemView.setOnClickListener((e)->
            {
                Intent intent = new Intent(activity, DisplayScreen.class);
                intent.putExtra("ırk",holder.textName.getText());
                holder.itemView.getContext().startActivity(intent);

            });



    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
        }
    }
}
