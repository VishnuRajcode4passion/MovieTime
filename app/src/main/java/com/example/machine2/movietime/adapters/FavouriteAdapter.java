package com.example.machine2.movietime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MoviesPosterResponse;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavouriteAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> results;
    ArrayList<String> ids;

    private static LayoutInflater inflater = null;

    public FavouriteAdapter(Context context, ArrayList<String> results, ArrayList<String> ids) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.results=results;
        this.ids = ids;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //getting the count of item
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return results.size();
    }

    // getting the item at particular position
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return getItem(position);
    }

    // getting the item id at particular position
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //a single row with required views is inflated into listview as many times depending on the count of items.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        //class to hold all the views in a single row.
        class ViewHolder {

            ImageView img;
            TextView movieId;
        }

        ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.single_row_image_adapter, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);
            holder.movieId = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        //Loading image from  url into imageView
        Picasso.with(context).load(String.valueOf(results.get(position))).resize(394, 400).into(holder.img);
        holder.movieId.setText(ids.get(position));
        return convertView;
    }
}

