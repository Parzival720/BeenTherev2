package cs.byu.edu.beentherev2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import cs.byu.edu.beentherev2.fragment.AddDialog;
import cs.byu.edu.beentherev2.fragment.EventCreationFragment;
import cs.byu.edu.beentherev2.fragment.EventFragment;
import cs.byu.edu.beentherev2.fragment.JournalFragment;
import cs.byu.edu.beentherev2.fragment.JournalCreationFragment;
import cs.byu.edu.beentherev2.fragment.JournalInsideFragment;
import cs.byu.edu.beentherev2.fragment.MapsFragment;
import cs.byu.edu.beentherev2.model.DataModel;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;
import cs.byu.edu.beentherev2.placeholder.JournalRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements JournalRecyclerViewAdapter.onJournalListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    Button addButton;

    private List<Journal> journals;
    private Journal currentJournal;
    private String previousWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        journals = new ArrayList<>();

        Journal one = new Journal();
        one.setTitle("IRELAND!!!");
        one.setStartDate(new GregorianCalendar(2020, Calendar.MAY, 11).getTime());
        one.setEndDate(new GregorianCalendar(2020, Calendar.MAY, 21).getTime());
        one.setDescription("I got to Ireland with my family and my best friends and it was amazing!");

        Event ireland1 = new Event();
        ireland1.setTitle("Cliffs of Moher");
        ireland1.setLocation(new LatLng(52.9720011201605, -9.430839508329006));
        ireland1.setCost(new Float(13.19));
        ireland1.setDescription("WOAH! I can't believe places like this exist outside of scenic documentaries");
        one.addEvent(ireland1);
        journals.add(one);

        currentJournal = one;

        Journal two = new Journal();
        two.setTitle("Alaska");
        two.setStartDate(new GregorianCalendar(2021, Calendar.AUGUST, 17).getTime());
        two.setEndDate(new GregorianCalendar(2021, Calendar.AUGUST, 27).getTime());
        two.setDescription("Alaska has some of the coolest scenery I've ever seen. I'm hoping we'll get to go back and do more soon");

        Event alaska1 = new Event();
        alaska1.setTitle("Nat Shack");
        alaska1.setLocation(new LatLng(61.12746893931103, -146.34576811494122));
        alaska1.setCost(new Float(15.36));
        alaska1.setDescription("Good fries. Better company. 10/10 would go again. 4 stars");
        two.addEvent(alaska1);
        journals.add(two);

        Journal three = new Journal();
        three.setTitle("Las Vegas");
        three.setStartDate(new GregorianCalendar(2021, Calendar.OCTOBER, 8).getTime());
        three.setEndDate(new GregorianCalendar(2021, Calendar.OCTOBER, 11).getTime());
        three.setDescription("OMG the food in vegas was AMAZING can't wait to go back with the girls :))) ");

        Event vegas1 = new Event();
        vegas1.setTitle("Eureka! Discover American Craft");
        vegas1.setLocation(new LatLng(36.168991532997886, -115.13967506349458));
        vegas1.setCost(new Float(23.14));
        vegas1.setDescription("Discover this cool brewery with friends. We got wasted");
        three.addEvent(vegas1);
        journals.add(three);


        DataModel[] drawerItem = new DataModel[2];

//        drawerItem[0] = new DataModel(R.drawable.house, "BeenThere");
        drawerItem[0] = new DataModel(R.drawable.journal, "Journals");
        drawerItem[1] = new DataModel(R.drawable.map_icon, "Map");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new JournalFragment()).commit();

        previousWindow = "Journals";
        mDrawerList.setItemChecked(0, true);
        mDrawerList.setSelection(0);
        setTitle(mNavigationDrawerItemTitles[0]);
        mDrawerLayout.closeDrawer(mDrawerList);

        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddDialog().show(
                        getSupportFragmentManager(), AddDialog.TAG);
            }
        });
    }

    public List<Journal> getJournals() { return journals; }

    public void addJournal(Journal journal) { journals.add(journal); }

    public Journal getCurrentJournal() { return currentJournal; }

    public void doPositiveClick() {
        Fragment fragment = new JournalCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("journal").commit();

        addButton = (Button) findViewById(R.id.add_button);
        addButton.setVisibility(View.GONE);
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        Fragment fragment = new EventCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("event").commit();

        addButton = (Button) findViewById(R.id.add_button);
        addButton.setVisibility(View.GONE);
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void doNeutralClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void popFromBackstack() {
        addButton = (Button) findViewById(R.id.add_button);
        addButton.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
        setTitle(previousWindow);
    }

    public void onJournalClick(int position) {
        currentJournal = journals.get(position);
        Fragment fragment = new JournalInsideFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("event").commit();
        setTitle(currentJournal.getTitle());
    }

    public void inflateEventList() {
        Fragment eventFragment = new EventFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.event_list_container, eventFragment).commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        // drop down menu items
        switch (position) {
            case 0:
                fragment = new JournalFragment();
                break;
            case 1:
                fragment = new MapsFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
            previousWindow = mNavigationDrawerItemTitles[position];

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
}
