package com.example.machine2.movietime.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machine2.movietime.MovieDatabase;
import com.example.machine2.movietime.MovieDetailResponse;
import com.example.machine2.movietime.MovieDetailsManager;
import com.example.machine2.movietime.MovieTrailerManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.Request;
import com.example.machine2.movietime.TrailerAdapter;
import com.example.machine2.movietime.UpdatedMovieDetails;
import com.example.machine2.movietime.UrlProvider;
import com.example.machine2.movietime.network.DetailsAdapter;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsActivity extends BaseActivity implements DetailsAdapter {
    ImageView poster;
    TextView durations;
    ImageView BackArrow;
    TextView Rating;
    TextView titles;
    TextView descriptions;
    TextView releasedate;
    ListView listView;
    Bundle bundle;
    CheckBox favorite;

    String title;
    String id;
    String trailerLink;
    String release_date;
    String overview;
    String posters;
    String image;

    Integer runtime;
    double rating;
    MovieDetailsManager movieDetailsManager;
    MovieTrailerManager movieTrailerManager;
    SharedPreferences preferences;
    MovieDatabase db = new MovieDatabase(this);
    Request request = new Request();
    Map<String, String> paramMap;
    RequestParams params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        BackArrow = (ImageView) findViewById(R.id.goBackArrow);
        poster = (ImageView) findViewById(R.id.movie_poster);
        durations = (TextView) findViewById(R.id.durationOfTheMovie);
        Rating = (TextView) findViewById(R.id.rating);
        titles = (TextView) findViewById(R.id.Title_of_movie);
        descriptions = (TextView) findViewById(R.id.movie_description);
        releasedate = (TextView) findViewById(R.id.year_of_relese);
        listView = (ListView) findViewById(R.id.listView);
        favorite = (CheckBox) findViewById(R.id.checkBox_favorite);

        bundle = getIntent().getExtras();
        id = bundle.getString("selectedId");

        movieDetailsManager = new MovieDetailsManager();
        movieDetailsManager.movieManager(this, id);

        movieTrailerManager = new MovieTrailerManager();
        movieTrailerManager.movieManager(this, this, id);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.textView6);
                trailerLink = (String) v.getText();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailerLink));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((buttonView.isChecked())) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", true); // value to store
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", false); // value to store
                    editor.commit();
                }
            }
        });
        preferences = getPreferences(MODE_PRIVATE);
        boolean tgpref = preferences.getBoolean("tgpref", false);  //default is true
        if (tgpref == true) //if (tgpref) may be enough, not sure
        {
            favorite.setChecked(true);
        } else {
            favorite.setChecked(false);
        }

        BackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void setMovieDetails(UpdatedMovieDetails detailResponse) {
        title = detailResponse.gettitle();
        runtime = detailResponse.getDuration();
        rating = detailResponse.getRatings();
        release_date = detailResponse.getReleaseDate();
        overview = detailResponse.getDescription();
        posters = detailResponse.getImage();
        titles.setText(title);
        durations.setText(String.valueOf(runtime));
        Rating.setText(String.valueOf(rating));
        releasedate.setText(release_date);
        descriptions.setText(overview);
        Picasso.with(this).load(posters).resize(394, 400).into(poster);
    }

    @Override
    public void movieTrailer(TrailerAdapter trailerAdapter) {
        listView.setAdapter(trailerAdapter);
    }

    public void addFavorite(View view) {
//        db.open();
//        image =  updatedMovieDetails.getImage();
//        db.insert(image, id);
//        db.close();

        db = new MovieDatabase(this);
        MovieDetailResponse movieDetailResponse = new MovieDetailResponse();
//        String imageUrl = movieDetailResponse.getPoster_path();
//        image = "https://image.tmdb.org/t/p/w500/" + imageUrl + request.getHeaders();

        String posterUrl = UrlProvider.POSTER_URL;
        String imageUrl = movieDetailResponse.getPoster_path();
        paramMap = request.getHeaders();
        params = new RequestParams(paramMap);
        image = posterUrl + imageUrl + params;
        System.out.println("poster url "+posterUrl);
        System.out.println("image url " + imageUrl);
        System.out.println("params "+params);
        db.open();
        db.insert(image, id);
        Toast.makeText(getApplicationContext(), "Added to Favoruite", Toast.LENGTH_LONG).show();
        db.close();
    }
}
