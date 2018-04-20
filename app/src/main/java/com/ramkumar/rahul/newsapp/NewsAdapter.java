package com.ramkumar.rahul.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private ProgressBar mProgressBar;

    public NewsAdapter(@NonNull Context context, @NonNull List<News> newsItems) {
        super(context,0, newsItems);
     }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_news_list_item, parent, false);
        }

        News currentNews = getItem(position);
        mProgressBar = listItemView.findViewById(R.id.progress_bar);

        ImageView articleImage = listItemView.findViewById(R.id.article_image_view);
        Picasso.get().load(currentNews.getImage()).into(articleImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });


        TextView headLine = listItemView.findViewById(R.id.headline_text_view);
        if(currentNews.getHeadline() != null && !currentNews.getHeadline().equals("null")) {
            headLine.setText(currentNews.getHeadline());
        }

        TextView description = listItemView.findViewById(R.id.description_text_view);
        if(currentNews.getDescription() != null && !currentNews.getDescription().equals("null")) {
            description.setText(currentNews.getDescription());
        }

        TextView source = listItemView.findViewById(R.id.source_text_view);
        if(currentNews.getSource() != null && !currentNews.getSource().equals("null")) {
            source.setText(currentNews.getSource());
        }


        TextView date = listItemView.findViewById(R.id.time_ago_text_view);
        try {
            date.setText(QueryUtils.getTimeAgo(currentNews.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listItemView;
    }
}
