package com.example.machine2.movietime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by machine3 on 5/20/16.
 */
public class MovieImageAdapter extends BaseAdapter {

    Context context;
    List<MoviesResponse.ResultsBean> results;
    MoviesResponse.ResultsBean item;
    UrlProvider urlProvider;

    ImageView imageView;
    TextView movieId;

    String posterUrl;
    String imageUrl;
    String image;

    private static LayoutInflater inflater = null;

    public MovieImageAdapter(Context context, List<MoviesResponse.ResultsBean> results) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.results = results;
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
        rowView = inflater.inflate(R.layout.single_row_image_adapter, null);
        item = (MoviesResponse.ResultsBean) getItem(position);
        posterUrl = urlProvider.posterUrl;

        movieId = (TextView) rowView.findViewById(R.id.textView);
        imageView = (ImageView) rowView.findViewById(R.id.imageView);

        int id = item.getId();
        imageUrl = item.getPoster_path();

        RequestParams params = new RequestParams();
        params.put("api_key", "efc0d91dd29ee74d0c55029e31266793");
        image = posterUrl + imageUrl + params;

        //Loading image from  url into imageView
        Picasso.with(context).load(image).resize(394, 400).into(imageView);
        movieId.setText(String.valueOf(id));
        return rowView;
    }
}