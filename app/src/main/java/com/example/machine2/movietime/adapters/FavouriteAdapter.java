package com.example.machine2.movietime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by machine2 on 27/05/16.
 */
public class FavouriteAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> results;
    ArrayList<String> ids;
    ImageView img;
    TextView textView;

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
        View rowView;
        rowView = inflater.inflate(R.layout.single_row_image_adapter, null);
        img = (ImageView) rowView.findViewById(R.id.imageView);
        //Loading image from  url into imageView
        Picasso.with(context).load(String.valueOf(results.get(position))).resize(394, 400).into(img);
        textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(ids.get(position));
        return rowView;
    }

}

