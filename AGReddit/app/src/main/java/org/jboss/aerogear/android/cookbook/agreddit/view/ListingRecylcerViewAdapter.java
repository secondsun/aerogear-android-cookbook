package org.jboss.aerogear.android.cookbook.agreddit.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jboss.aerogear.android.cookbook.agreddit.R;
import org.jboss.aerogear.android.cookbook.agreddit.reddit.Listing;
import org.jboss.aerogear.android.cookbook.agreddit.reddit.T3;
import org.jboss.aerogear.android.impl.pipeline.paging.WrappingPagedList;

import java.util.Collections;
import java.util.List;

/**
 * Created by summers on 12/18/14.
 */
public class ListingRecylcerViewAdapter  extends RecyclerView.Adapter<ListingRecylcerViewAdapter.ViewHolder> {
    private List<T3> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mLayout;

        public ViewHolder(RelativeLayout v) {
            super(v);
            mLayout = v;
        }
    }

    public ListingRecylcerViewAdapter(Listing listing) {
        mDataset = Collections.unmodifiableList(listing.getData().getChildren());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_story_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T3 story = mDataset.get(position);
        RelativeLayout layout = holder.mLayout;
        String author = story.getAuthor();
        String title = story.getTitle();
        Long ups = story.getScore();
        String subreddit = story.getSubreddit();

        ((TextView) layout.findViewById(R.id.title))
                .setText(title);
        ((TextView) layout.findViewById(R.id.author))
                .setText(author);
        ((TextView) layout.findViewById(R.id.upvotes)).setText(ups
                + "");
        ((TextView) layout.findViewById(R.id.subreddit))
                .setText(subreddit);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
