package com.ramkumar.rahul.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsItemLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsItemLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<News> loadInBackground() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return QueryUtils.extractNewsItems(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
