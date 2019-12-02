package com.tomjerry.undangaelektronikproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomjerry.undangaelektronikproject.R;
import com.tomjerry.undangaelektronikproject.model.Tamu;

import java.util.ArrayList;


public class AdapterDataTamu extends RecyclerView.Adapter<AdapterDataTamu.FavoriteViewHolder> {

    private ArrayList<Tamu> listTamu = new ArrayList<>();
    private Context context;

    public AdapterDataTamu(Context context) {
        this.context = context;
    }

    public ArrayList<Tamu> getListTamu() {
        return listTamu;
    }

    public void setListTamu(ArrayList<Tamu> listTamu) {
        if (listTamu.size() > 0) {
            this.listTamu.clear();
        }
        this.listTamu = listTamu;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tamu, viewGroup, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder favoriteViewHolder, int i) {
        final Tamu modelMovie = listTamu.get(i);
        String nama = modelMovie.getNama();
        String alamat = modelMovie.getAlamat();
        String nomor = String.valueOf(modelMovie.getNo());

        favoriteViewHolder.teksNama.setText(nama);
        favoriteViewHolder.teksAlamat.setText(alamat);
        favoriteViewHolder.teksNo.setText(nomor);
//        String alamat = modelMovie.getAlamat();
//        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent moveDataFilm = new Intent(context, DetailFilm.class);
//                moveDataFilm.putExtra(DetailFilm.EXTRA_BACKGROUND, modelMovie.getBackdropPath());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_TITLE, modelMovie.getTitle());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_DATE, modelMovie.getReleaseDate());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_POPULARITY, modelMovie.getPopularity());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_RATING, modelMovie.getVoteAverage());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_DESC, modelMovie.getOverview());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_PHOTO, modelMovie.getPosterPath());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_ID, modelMovie.getId());
//                moveDataFilm.putExtra(DetailFilm.EXTRA_VOTE_COUNT, modelMovie.getVoteCount());
//
//                context.startActivity(moveDataFilm);
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listTamu.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView teksNama , teksAlamat , teksNo;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            teksNama = itemView.findViewById(R.id.txtNama1);
            teksAlamat = itemView.findViewById(R.id.txtAlamat1);
            teksNo = itemView.findViewById(R.id.txtNo1);
        }
    }
}

