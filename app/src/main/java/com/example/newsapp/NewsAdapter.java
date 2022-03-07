package com.example.newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.modal.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.HolderStat> {
    Context context;
    ArrayList<News> newsArrayList;
    OnNewsClickListner onNewsClickListner;


    public NewsAdapter(Context context, ArrayList<News> newsArrayList,OnNewsClickListner onNewsClickListner) {
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.onNewsClickListner=onNewsClickListner;
    }

    @NonNull
    @Override
    public HolderStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false);
        return new HolderStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStat holder, int position) {
        News news = newsArrayList.get(position);

        //get 4
        String Title = news.getTitle();
        String Auth = news.getAuthor();
        String News = news.getName();
        String img = news.getUrlToImage();
        String url = news.getUrl();

        //set
        holder.titleText.setText(Title);
        holder.newsText.setText(News);
        holder.authText.setText(Auth);
        Picasso.get().load(img).into(holder.newsImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onNewsClickListner.OnNewsClick(news);
            }
        });


   }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }


    class HolderStat extends RecyclerView.ViewHolder {
        TextView titleText, authText, newsText;
        ImageView newsImg;

        public HolderStat(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            authText = itemView.findViewById(R.id.authText);
            newsText = itemView.findViewById(R.id.newsNameText);
            newsImg = itemView.findViewById(R.id.newsImg);

        }

    }

    public interface OnNewsClickListner{
        void OnNewsClick(News news);
    }

}
