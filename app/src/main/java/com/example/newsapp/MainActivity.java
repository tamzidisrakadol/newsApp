package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.modal.News;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnNewsClickListner{
    ArrayList<News> newsArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    private static final String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        loadNews();

    }

    private void loadNews() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("articles");
                for (int i = 0; i < jsonArray.length(); i++) {
                    News news = new News();
                    String newsimg = jsonArray.getJSONObject(i).getString("urlToImage");
                    String newstitle = jsonArray.getJSONObject(i).getString("title");
                    String newsAuth = jsonArray.getJSONObject(i).getString("author");
                    String newsName = jsonArray.getJSONObject(i).getJSONObject("source").getString("name");
                    String newsUrl = jsonArray.getJSONObject(i).getString("url");

                    news.setUrl(newsUrl);
                    news.setUrlToImage(newsimg);
                    news.setName(newsName);
                    news.setAuthor(newsAuth);
                    news.setTitle(newstitle);
                    newsArrayList.add(news);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            newsAdapter = new NewsAdapter(getApplicationContext(), newsArrayList,this);
            recyclerView.setAdapter(newsAdapter);

        }, error -> {

        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void OnNewsClick(News news) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(news.getUrl()));
    }
}