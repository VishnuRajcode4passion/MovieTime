package com.example.machine2.movietime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.models.Requests;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.models.MoviesPosterResponse;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by machine3 on 5/20/16.
 */
public class MovieImageAdapter extends BaseAdapter {

    Context context;
    List<MoviesPosterResponse.ResultsBean> results;
    MoviesPosterResponse.ResultsBean item;
    Map<String, String> paramMap;
    RequestParams params;

    private static LayoutInflater inflater = null;
    private Requests request = new Requests();

    public MovieImageAdapter(Context context, List<MoviesPosterResponse.ResultsBean> results) {

        // TODO Auto-generated constructor stub
        this.context = context;
        this.results = results;
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
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //class to hold all the views in a single row.
        class ViewHolder {

            ImageView imageView;
            TextView movieId;
            String posterUrl;
            String imageUrl;
            String image;
        }

        ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.single_row_image_adapter, null);
            holder = new ViewHolder();
            holder.movieId = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        item = (MoviesPosterResponse.ResultsBean) getItem(position);
        int id = item.getId();

        holder.posterUrl = UrlProvider.POSTER_URL;
        holder.imageUrl = item.getPoster_path();
        paramMap = request.getHeaders();
        params = new RequestParams(paramMap);
        holder.image = holder.posterUrl + holder.imageUrl + params;

        //Loading image from  url into imageView
        Picasso.with(context).load(holder.image).resize(394, 400).into(holder.imageView);
        holder.movieId.setText(String.valueOf(id));

        return convertView;
    }
}