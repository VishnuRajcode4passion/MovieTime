package com.example.machine2.movietime.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.machine2.movietime.MovieDetailsManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.SetDetails;
import com.example.machine2.movietime.network.DetailsAdapter;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsActivity extends BaseActivity implements DetailsAdapter{
    ImageView poster;
    TextView durations;
    ImageView BackArrow;
    TextView Rating;
    TextView titles;
    TextView descriptions;
    TextView releasedate;
    ListView listView;
    Bundle bundle;

    String title;
    String id;
    String trailerLink;
    MovieDetailsManager movieDetailsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        BackArrow = (ImageView)findViewById(R.id.goBackArrow);
        poster =(ImageView)findViewById(R.id.movie_poster);
        durations = (TextView)findViewById(R.id.durationOfTheMovie);
        Rating = (TextView)findViewById(R.id.rating);
        titles=(TextView)findViewById(R.id.Title_of_movie);
        descriptions=(TextView)findViewById(R.id.movie_description);
        releasedate=(TextView)findViewById(R.id.year_of_relese);
        listView = (ListView) findViewById(R.id.listView);

        bundle=getIntent().getExtras();
        id=bundle.getString("selectedId");
        movieDetailsManager = new MovieDetailsManager();
        movieDetailsManager.movieManager(this,id);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView v = (TextView) view.findViewById(R.id.textView6);
              //  trailerLink = (String) v.getText();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailerLink));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        BackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void setMovieDetails(SetDetails detail) {
        String title = detail.gettitle().toString();
        titles.setText(title);

    }
}
