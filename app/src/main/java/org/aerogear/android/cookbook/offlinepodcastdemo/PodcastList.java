package org.aerogear.android.cookbook.offlinepodcastdemo;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PodcastList extends ActionBarActivity {

    private RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_list);
        listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager( getApplicationContext() ));
        listView.setAdapter(new PodcastAdapter());
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPodcasts();
    }

    private void loadPodcasts() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                SyndFeedInput input = new SyndFeedInput();
                try {
                    List<SyndFeed> feeds = new ArrayList<SyndFeed>(1);

                    SyndFeed feed = input.build(new XmlReader(getResources().openRawResource(R.raw.synd_1)));
                    feeds.add(feed);

                    feed = input.build(new XmlReader(getResources().openRawResource(R.raw.synd_3)));
                    feeds.add(feed);

                    feed = input.build(new XmlReader(getResources().openRawResource(R.raw.synd_3)));
                    feeds.add(feed);

                    feed = input.build(new XmlReader(getResources().openRawResource(R.raw.synd_4)));
                    feeds.add(feed);

                    feed = input.build(new XmlReader(getResources().openRawResource(R.raw.synd_5)));
                    feeds.add(feed);

                    return feeds;
                } catch (FeedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                listView.setAdapter(new PodcastAdapter((List<SyndFeed>) o));
            }
        }.execute();
    }
}
