package com.example.machine2.movietime;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.machine2.movietime.network.NetworkCommunicator;

/**
 * Created by machine2 on 23/05/16.
 */
public class MenuSelector {

    Context context;
    NetworkCommunicator networkCommunication;
    DrawerLayout drawer;
    String title;

    public MenuSelector(Context context, NetworkCommunicator networkCommunication, DrawerLayout drawer) {
        this.context = context;
        this.networkCommunication = networkCommunication;
        this.drawer = drawer;
    }

    public String getItem(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
//            networkCommunication.topRatedMovies((NetworkListener) context);
            title = "Top Rated";

//        } else if (id == R.id.popular) {
//            networkCommunication.popularMovies((NetworkListener) context);
//            title = "Popular";
//        } else if (id == R.id.favorite) {} else if (id == R.id.logout) {
//            drawer.closeDrawer(GravityCompat.START);
//            Intent intent = new Intent(context,LoginActivity.class);
//            context.startActivity(intent);
//        }
        }
        return title;
    }
}

