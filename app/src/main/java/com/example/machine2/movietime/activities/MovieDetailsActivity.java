package com.example.machine2.movietime.activities;

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

import com.example.machine2.movietime.Constants;
import com.example.machine2.movietime.database.MovieDatabase;
import com.example.machine2.movietime.database.MovieDatabaseManager;
import com.example.machine2.movietime.controllers.MovieDetailsManager;
import com.example.machine2.movietime.controllers.MovieTrailerManager;
import com.example.machine2.movietime.R;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.models.UpdatedMovieDetails;
import com.example.machine2.movietime.interfaces.MovieDetailsListener;
import com.squareup.picasso.Picasso;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsActivity extends BaseActivity implements MovieDetailsListener {

    ImageView poster;
    TextView durations;
    ImageView backArrow;
    TextView Rating;
    TextView titles;
    TextView descriptions;
    TextView releaseDate;
    ListView listView;
    Bundle bundle;
    CheckBox favorite;

    SharedPreferences preferences;

    String id;
    String posters;


    double rating;
    MovieDetailsManager movieDetailsManager;
    MovieTrailerManager movieTrailerManager;
    MovieDatabaseManager databaseManager;


    MovieDatabase db = new MovieDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        backArrow = (ImageView) findViewById(R.id.goBackArrow);
        poster = (ImageView) findViewById(R.id.movie_poster);
        durations = (TextView) findViewById(R.id.durationOfTheMovie);
        Rating = (TextView) findViewById(R.id.rating);
        titles = (TextView) findViewById(R.id.Title_of_movie);
        descriptions = (TextView) findViewById(R.id.movie_description);
        releaseDate = (TextView) findViewById(R.id.year_of_relese);
        listView = (ListView) findViewById(R.id.listView);
        favorite = (CheckBox) findViewById(R.id.checkBox_favorite);

        bundle = getIntent().getExtras();
        id = bundle.getString("selectedId");

        showDialog();

        movieDetailsManager = new MovieDetailsManager();
        movieDetailsManager.getMovieDetails(this, id);

        movieTrailerManager = new MovieTrailerManager();
        movieTrailerManager.getTrailerManager(this, this, id);

        databaseManager = new MovieDatabaseManager();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String trailerLink;

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
                    editor.putBoolean(Constants.PREFERENCES_TEXT, true); // value to store
                    editor.commit();
                } else {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(Constants.PREFERENCES_TEXT, false); // value to store
                    editor.commit();
                }
            }
        });

        preferences = getPreferences(MODE_PRIVATE);
        boolean tgpref = preferences.getBoolean(Constants.PREFERENCES_TEXT, false);  //default is true
        if (tgpref == true) {
            favorite.setChecked(true);
        } else {
            favorite.setChecked(false);
        }

        backArrow.setOnClickListener(new View.OnClickListener() {
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

        String title;
        String release_date;
        String overview;

        Integer Movieruntime;

        dismissDialog();

        title = detailResponse.getTitle();
        Movieruntime = detailResponse.getDuration();
        rating = detailResponse.getRatings();
        release_date = detailResponse.getReleaseDate();
        overview = detailResponse.getDescription();
        posters = detailResponse.getImage();

        titles.setText(title);
        durations.setText(String.valueOf(Movieruntime) + " minutes");
        Rating.setText(String.valueOf(rating) + "/10");
        releaseDate.setText(release_date);
        descriptions.setText(overview);
        Picasso.with(this).load(posters).resize(394, 400).into(poster);
    }

    @Override
    public void movieTrailer(MovieTrailerAdapter movieTrailerAdapter) {

        listView.setAdapter(movieTrailerAdapter);
    }

    @Override
    public void setErrorMessage(String statusMessage) {

        dismissDialog();

        Toast.makeText(this, statusMessage, Toast.LENGTH_LONG).show();
    }


    public void addFavorite(View view) {

        if (((CheckBox) view).isChecked()) {

            databaseManager.setFavorite(posters, id, db);

        }
        else {

            databaseManager.removeFavorites(id);

        }

    }



}
