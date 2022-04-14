package com.example.sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.MainActivity;
import com.example.sqlite.R;
import com.example.sqlite.database.DBcontroller;
import com.example.sqlite.database.Teman;
import com.example.sqlite.edit_teman;

import com.example.sqlite.R;
import com.example.sqlite.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> ListData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.ListData = listData;
    }

    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinf = LayoutInflater.from(parent.getContext());
        View view = layoutinf.inflate(R.layout.row_data_teman, parent, false);
        control = parent.getContext();
        return new TemanViewHolder(view);


    }

    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nm, tlp, id;

        id = ListData.get(position).getId();
        nm = ListData.get(position).getNama();
        tlp = ListData.get(position).getTelpon();
        DBcontroller db = new DBcontroller(control);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(control, holder.cardku);
                popupMenu.inflate(R.menu.menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem Item) {
                        switch (Item.getItemId()) {
                            case R.id.mnEdit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id", id);
                                i.putExtra("nama", nma);
                                i.putExtra("telpon", tlp);
                                control.startActivity(i);
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {

        return (ListData != null) ? ListData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {

        private CardView cardku;
        private TextView namaTxt, telponTxt;

        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.recyclerView);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}

