package sg.edu.rp.webservices.mydatabook;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MenuItem> drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ArrayAdapter aa;
    ActionBar ab;
    String currentTitle;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerItems = new ArrayList<MenuItem>();

        MenuItem menu1 = new MenuItem("Bio", android.R.drawable.ic_dialog_info);
        MenuItem menu2 = new MenuItem("Vaccination", android.R.drawable.stat_sys_warning);
        MenuItem menu3 = new MenuItem("Anniversary", android.R.drawable.ic_menu_my_calendar);
        MenuItem menu4 = new MenuItem("About Us", android.R.drawable.star_big_on);

        drawerItems.add(menu1);
        drawerItems.add(menu2);
        drawerItems.add(menu3);
        drawerItems.add(menu4);
        ab = getSupportActionBar();

        aa = new MenuAdapter(this, R.layout.row, drawerItems);

        drawerList.setAdapter(aa);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Fragment fragment = null;
                if (position != 3) {

                    if (position == 0) {
                        fragment = new BioFragment();
                    } else if (position == 1) {
                        fragment = new VaccinationFragment();
                    } else if (position == 2) {
                        fragment = new AnniversaryFragment();
                    }  else {

                    }
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction trans = fm.beginTransaction();
                    trans.replace(R.id.content_frame, fragment);
                    trans.commit();
                }else{
                        Intent i = new Intent(getBaseContext(), AboutUsActivity.class);
                        startActivity(i);

                }

                // Highlight the selected item,
                //  update the title, and close the drawer
                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems.get(position).getName();
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);

            }
        });


        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,      /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Would be called when a drawer has completely closed */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            /** Would be called when a drawer has completely open */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
