package com.example.machine2.movietime.activities;

import android.content.Intent;
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

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.adapters.MovieTrailerAdapter;
import com.example.machine2.movietime.controllers.MovieDatabaseManager;
import com.example.machine2.movietime.controllers.MovieDetailsListener;
import com.example.machine2.movietime.controllers.MovieDetailsManager;
import com.example.machine2.movietime.controllers.MovieTrailerManager;
import com.example.machine2.movietime.models.UpdatedMovieDetails;
import com.squareup.picasso.Picasso;

/**
 * Created by machine2 on 26/05/16.
 */
public class MovieDetailsActivity extends BaseActivity implements MovieDetailsListener {

    ImageView poster;
    ImageView backArrow;


    TextView Rating;
    TextView titles;
    TextView durations;
    TextView descriptions;
    TextView releaseDate;

    ListView listView;

    Bundle bundle;

    CheckBox favorite;

    String id;
    String posters;

    MovieDetailsManager movieDetailsManager;
    MovieTrailerManager movieTrailerManager;
    MovieDatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        backArrow = (ImageView) findViewById(R.id.goBackArrow);
        poster    = (ImageView) findViewById(R.id.movie_poster);

        durations = (TextView) findViewById(R.id.durationOfTheMovie);
        Rating    = (TextView) findViewById(R.id.rating);
        titles    = (TextView) findViewById(R.id.Title_of_movie);
        descriptions = (TextView) findViewById(R.id.movie_description);
        releaseDate  = (TextView) findViewById(R.id.year_of_relese);

        listView     = (ListView) findViewById(R.id.listView);
        favorite     = (CheckBox) findViewById(R.id.checkBox_favorite);

        //to receive the movie id from MainActivity.
        bundle = getIntent().getExtras();
        id = bundle.getString("selectedId");

        showDialog();

        movieDetailsManager = new MovieDetailsManager();
        movieDetailsManager.getMovieDetails(this, this, id);

        movieTrailerManager = new MovieTrailerManager();
        movieTrailerManager.getTrailerManager(this, this, id);


        databaseManager = new MovieDatabaseManager(this);

        //to check the state of favourite button during loading the MovieDetailsActivity.
        String state = databaseManager.getState(id);


        if (state != null) {

            if (state.equals("checked")) {
                favorite.setChecked(true);
            }
        }

        //to show the trailer of a particular movie in youtube.
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

        //when favourite button is checked,insert favourite movie into data base,otherwise delete movie from data
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ((buttonView.isChecked() == true)) {

                    databaseManager.setFavorite(posters, id, "checked");
                } else {


                    databaseManager.removeFavorites(id);

                }
            }
        });


        //to navigate back to main screen.

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                intent = new Intent(MovieDetailsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    //to set the details of a movie.
    @Override
    public void setMovieDetails(UpdatedMovieDetails detailResponse) {

        String title;
        String release_date;
        String overview;
        Integer runtime;
        double rating;

        dismissDialog();

        title = detailResponse.getTitle();
        runtime = detailResponse.getDuration();
        rating = detailResponse.getRatings();
        release_date = detailResponse.getReleaseDate();
        overview = detailResponse.getDescription();
        posters = detailResponse.getImage();

        titles.setText(title);
        durations.setText(String.valueOf(runtime) + " minutes");
        Rating.setText(String.valueOf(rating) + "/10");
        releaseDate.setText(release_date);
        descriptions.setText(overview);
        Picasso.with(this).load(posters).resize(394, 400).into(poster);
    }

    //to set all the trailers in listview.
    @Override
    public void movieTrailer(MovieTrailerAdapter movieTrailerAdapter) {

        listView.setAdapter(movieTrailerAdapter);
    }

    //to display the error message ,if there is problem in fetching the contents from server.
    @Override
    public void setErrorMessage(String statusMessage) {

        dismissDialog();

        Toast.makeText(this, statusMessage, Toast.LENGTH_LONG).show();
    }
}
