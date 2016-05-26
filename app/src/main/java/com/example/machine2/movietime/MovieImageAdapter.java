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
import java.util.Map;

/**
 * Created by machine3 on 5/20/16.
 */
public class MovieImageAdapter extends BaseAdapter {

    Context context;
    List<MoviesResponse.ResultsBean> results;
    MoviesResponse.ResultsBean item;
    Map<String, String> paramMap;
    RequestParams params;

    ImageView imageView;
    TextView movieId;

    String posterUrl;
    String imageUrl;
    String image;

    private static LayoutInflater inflater = null;
    private Request request = new Request();

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
        posterUrl = UrlProvider.POSTER_URL;

        movieId = (TextView) rowView.findViewById(R.id.textView);
        imageView = (ImageView) rowView.findViewById(R.id.imageView);

        int id = item.getId();
        imageUrl = item.getPoster_path();

        paramMap = request.getHeaders();
        params = new RequestParams(paramMap);
        image = posterUrl + imageUrl + params;

        //Loading image from  url into imageView
        Picasso.with(context).load(image).resize(394, 400).into(imageView);
        movieId.setText(String.valueOf(id));
        return rowView;
    }
}