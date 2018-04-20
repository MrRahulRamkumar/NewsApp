package com.ramkumar.rahul.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mNewsAdapter;
    private static final String SAMPLE_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=23e02c8a8435453cbd9b82af5cfd2ed7";
    private ConnectivityManager mConnMgr;
    private NetworkInfo mNetworkInfo;
    private Button mTryAgain;
    private ProgressBar mProgressBar;
    private TextView mTextView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTryAgain = findViewById(R.id.try_again);
        mTryAgain.setVisibility(View.INVISIBLE);

        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        mTextView = findViewById(R.id.fetching_news);

        ListView newsListView = findViewById(R.id.list);
        mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(mNewsAdapter);

        checkNetworkAndDownloadData();

        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(R.string.fetching_news);
                mProgressBar.setVisibility(View.VISIBLE);
                mTryAgain.setVisibility(View.INVISIBLE);
                checkNetworkAndDownloadData();
            }
        });

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri newItemUri = Uri.parse(mNewsAdapter.getItem(position).getArticleUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newItemUri);
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsItemLoader(this, SAMPLE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        if(data != null && !data.isEmpty()) {
            mTextView.setVisibility(View.GONE);
            mTryAgain.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);

            mNewsAdapter.clear();
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

    private void checkNetworkAndDownloadData() {
        mConnMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnMgr.getActiveNetworkInfo();

        if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, MainActivity.this);
        } else {
            showSnackbar(findViewById(R.id.main_activity),"Please check your network connectivity", Snackbar.LENGTH_INDEFINITE);
            mTextView.setText(R.string.can_t_load_news);
            mTryAgain.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void showSnackbar(View view, String message, int duration) {

        final Snackbar snackbar = Snackbar.make(view, message, duration);
        snackbar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
