package com.example.machine2.movietime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machine2.movietime.models.MovieTrailerResponse;

import java.util.List;

/**
 * Created by machine2 on 27/05/16.
 */
public class TrailerAdapter extends BaseAdapter{
    Context context;
    List<MovieTrailerResponse.ResultsBean> results;
    MovieTrailerResponse.ResultsBean item;
    String key;
    String trailerLink;

    private static LayoutInflater inflater = null;

    public TrailerAdapter(Context context, List<MovieTrailerResponse.ResultsBean> results) {
        this.context = context;
        this.results=results;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //getting the count of item
    @Override
    public int getCount() {
        // TODO Auto-generatjava.lang.Stringed method stub
        return results.size();
    }

    // getting the item at particular position
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return results.get(position);
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
        rowView = inflater.inflate(R.layout.single_row_trailer_adapter, null);
        TextView textView = (TextView) rowView.findViewById(R.id.textView5);
        TextView trailer = (TextView) rowView.findViewById(R.id.textView6);
        item = (MovieTrailerResponse.ResultsBean) getItem(position);
        key = item.getKey();
        trailerLink = "https://www.youtube.com/watch?v="+key;
        textView.setText("Trailer "+(position+1));
        trailer.setText(trailerLink);
        return rowView;
    }
}



