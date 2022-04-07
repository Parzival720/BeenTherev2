package cs.byu.edu.beentherev2;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cs.byu.edu.beentherev2.fragment.AddDialog;
import cs.byu.edu.beentherev2.fragment.EventCreationFragment;
import cs.byu.edu.beentherev2.fragment.HomeFragment;
import cs.byu.edu.beentherev2.fragment.JournalFragment;
import cs.byu.edu.beentherev2.fragment.JournalCreationFragment;
import cs.byu.edu.beentherev2.fragment.MapsFragment;
import cs.byu.edu.beentherev2.model.DataModel;
import cs.byu.edu.beentherev2.model.Journal;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    Button addButton;

    private List<Journal> journals;

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
        one.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        one.setEndDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        one.setDescription("The cliffs of Moher were one of my favorite places to visit in " +
                "Ireland. They were so pretty!");
        journals.add(one);


        Journal two = new Journal();
        two.setTitle("IRELAND!!!");
        two.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        two.setEndDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        two.setDescription("The cliffs of Moher were one of my favorite places to visit in " +
                "Ireland. They were so pretty!");
        journals.add(two);

        Journal three = new Journal();
        three.setTitle("IRELAND!!!");
        three.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        three.setEndDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        three.setDescription("The cliffs of Moher were one of my favorite places to visit in " +
                "Ireland. They were so pretty!");
        journals.add(three);


        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.house, "BeenThere");
        drawerItem[1] = new DataModel(R.drawable.journal, "Journals");
        drawerItem[2] = new DataModel(R.drawable.map_icon, "Map");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();

        mDrawerList.setItemChecked(0, true);
        mDrawerList.setSelection(0);
        setTitle(mNavigationDrawerItemTitles[0]);
        mDrawerLayout.closeDrawer(mDrawerList);

        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddDialog().show(
                        getSupportFragmentManager(), AddDialog.TAG);
            }
        });
    }

    public List<Journal> getJournals() { return journals; }

    public void doPositiveClick() {
        Fragment fragment = new JournalCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("journal").commit();
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        Fragment fragment = new EventCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("event").commit();
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void doNeutralClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void popFromBackstack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
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
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new JournalFragment();
                break;
            case 2:
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
