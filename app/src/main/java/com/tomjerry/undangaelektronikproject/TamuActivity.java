package com.tomjerry.undangaelektronikproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.tomjerry.undangaelektronikproject.adapter.AdapterDataTamu;
import com.tomjerry.undangaelektronikproject.db.TamuHelper;
import com.tomjerry.undangaelektronikproject.model.Tamu;


public class TamuActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamu);

        recyclerView = findViewById(R.id.recylerview);

        setRecyclerView();
    }

    private void setRecyclerView(){
        AdapterDataTamu favoriteAdapter = new AdapterDataTamu(this);
        favoriteAdapter.notifyDataSetChanged();
        TamuHelper favoriteHelper = TamuHelper.getInstance(this);
        favoriteAdapter.setListTamu(favoriteHelper.getAllTamu());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
