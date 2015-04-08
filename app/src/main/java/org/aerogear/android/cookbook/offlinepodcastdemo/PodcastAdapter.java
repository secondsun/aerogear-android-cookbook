package org.aerogear.android.cookbook.offlinepodcastdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun.syndication.feed.synd.SyndFeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 4/8/15.
 */
public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.Podcast> {


    private List<SyndFeed> feedList = new ArrayList<>();


    public PodcastAdapter() {
    }

    public PodcastAdapter( List<SyndFeed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public Podcast onCreateViewHolder(ViewGroup parent, int viewType) {

        return new Podcast(LayoutInflater.from(parent.getContext()).inflate(R.layout.synfeed_item, null));
    }

    @Override
    public void onBindViewHolder(Podcast holder, int position) {
        SyndFeed feed = feedList.get(position);
        holder.title.setText(feed.getTitle());
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    /**
     * Created by summers on 4/8/15.
     */
    public static class Podcast extends RecyclerView.ViewHolder {

        TextView title;

        public Podcast(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}