package morrison.com.memento;

import android.accounts.Account;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import morrison.com.memento.Additional.FriendsActivity;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private JSONObject responseUserData;
    private NavigationView navigationView;
    private CircleImageView userPictureImageView;
    private TextView userNameTextView;
    private TextView userEmailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setNavigationHeader();
        setUserProfile(getIntent().getStringExtra("user_data"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUserProfile(String userData) {

        try {

            responseUserData = new JSONObject(new JSONObject(userData).get("data").toString());
            userEmailTextView.setText(responseUserData.get("user_email").toString());
            userNameTextView.setText(responseUserData.get("user_name").toString());
            Toast.makeText(this, responseUserData.get("user_avatar_link").toString(), Toast.LENGTH_LONG).show();
            Picasso.with(this).load(
                    "https://memento-service.000webhostapp.com/php/uploads/"
                            +responseUserData.get("user_avatar_link")
                            .toString())
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.ic_account_circle)
                            .error(R.drawable.ic_account_circle)
                            .into(userPictureImageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setNavigationHeader() {

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_profile, null);
        navigationView.addHeaderView(header);
        userPictureImageView = (CircleImageView) header.findViewById(R.id.profile_image);
        userNameTextView = (TextView) header.findViewById(R.id.user_name);
        userEmailTextView = (TextView) header.findViewById(R.id.user_email);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_event) {
            // Handle the camera action
        } else if (id == R.id.nav_friends) {

            CustomStartActivity.executeActivity(ProfileActivity.this,
                    FriendsActivity.class, "", "");

        } else if (id == R.id.nav_blocked_users) {

        } else if (id == R.id.nav_user_account_settings) {

            CustomStartActivity.executeActivity(ProfileActivity.this,
                    AccountSettings.class, "user_data", responseUserData.toString());

        } else if (id == R.id.nav_app_settings) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
