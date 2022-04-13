package cs.byu.edu.beentherev2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
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

    Toolbar toolbar;
    private CharSequence mTitle;


    ImageButton addButton;

    private List<Journal> journals;
    private Journal currentJournal;
    private String previousWindow;

    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = getTitle();

        setupToolbar();

        currentLocation = null;

        journals = new ArrayList<>();

        Journal one = new Journal();
        one.setTitle("IRELAND!!!");
        one.setStartDate(new GregorianCalendar(2020, Calendar.MAY, 11).getTime());
        one.setEndDate(new GregorianCalendar(2020, Calendar.MAY, 21).getTime());
        one.setDescription("I got to Ireland with my family and my best friends and it was amazing!");
        one.setPhoto(R.drawable.moher);

        Event ireland1 = new Event();
        ireland1.setTitle("Cliffs of Moher");
        ireland1.setLocation(new LatLng(52.9720011201605, -9.430839508329006));
        ireland1.setCost(new Float(0));
        ireland1.setStartDate(new GregorianCalendar(2020, Calendar.MAY, 17).getTime());
        ireland1.setEndDate(new GregorianCalendar(2020, Calendar.MAY, 18).getTime());
        ireland1.setDescription("WOAH! I can't believe places like this exist outside of scenic documentaries");
        ireland1.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.moher))));
        ireland1.setTags(new ArrayList<String>(Arrays.asList("Hike", "Cliffs", "UK")));
        one.addEvent(ireland1);

        Event ireland2 = new Event();
        ireland2.setTitle("Blarney Stone");
        ireland2.setLocation(new LatLng(51.933949, -8.559486));
        ireland2.setCost(new Float(0));
        ireland2.setStartDate(new GregorianCalendar(2020, Calendar.MAY, 19).getTime());
        ireland2.setDescription("Kissed the stone. Probably got a couple rare diseases");
        ireland2.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.blarney))));
        ireland2.setTags(new ArrayList<String>(Arrays.asList("Historic", "UK")));
        one.addEvent(ireland2);

        Event ireland3 = new Event();
        ireland3.setTitle("The Old Quarter Pub");
        ireland3.setLocation(new LatLng(52.664256, -8.624549));
        ireland3.setCost(new Float(26.35));
        ireland3.setStartDate(new GregorianCalendar(2020, Calendar.MAY, 19).getTime());
        ireland3.setDescription("Drank apple juice in limerick with some dirty irishmen. Well their poems were dirty ;)");
        ireland3.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.pub))));
        ireland3.setTags(new ArrayList<String>(Arrays.asList("Bar", "Food", "UK")));
        one.addEvent(ireland3);

        journals.add(one);

        currentJournal = one;

        Journal two = new Journal();
        two.setTitle("Alaska");
        two.setStartDate(new GregorianCalendar(2021, Calendar.AUGUST, 17).getTime());
        two.setEndDate(new GregorianCalendar(2021, Calendar.AUGUST, 27).getTime());
        two.setDescription("Alaska has some of the coolest scenery I've ever seen. I'm hoping we'll get to go back and do more soon");
        two.setPhoto(R.drawable.alaska);

        Event alaska1 = new Event();
        alaska1.setTitle("Nat Shack");
        alaska1.setLocation(new LatLng(61.12746893931103, -146.34576811494122));
        alaska1.setCost(new Float(15.36));
        alaska1.setStartDate(new GregorianCalendar(2021, Calendar.AUGUST, 17).getTime());
        alaska1.setDescription("Good fries. Better company. 10/10 would go again. 4 stars");
        alaska1.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.nat_shack))));
        alaska1.setTags(new ArrayList<String>(Arrays.asList("Food", "USA")));
        two.addEvent(alaska1);
        journals.add(two);

        Journal three = new Journal();
        three.setTitle("Las Vegas");
        three.setStartDate(new GregorianCalendar(2021, Calendar.OCTOBER, 8).getTime());
        three.setEndDate(new GregorianCalendar(2021, Calendar.OCTOBER, 11).getTime());
        three.setDescription("OMG the food in vegas was AMAZING can't wait to go back with the girls :))) ");
        three.setPhoto(R.drawable.vegas);

        Event vegas1 = new Event();
        vegas1.setTitle("Eureka! Discover American Craft");
        vegas1.setStartDate(new GregorianCalendar(2021, Calendar.OCTOBER, 8).getTime());
        vegas1.setLocation(new LatLng(36.168991532997886, -115.13967506349458));
        vegas1.setCost(new Float(53.14));
        vegas1.setDescription("Discovered this cool brewery that has massive burgers!! Need to bring dad");
        vegas1.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.eureka))));
        vegas1.setTags(new ArrayList<String>(Arrays.asList("Food", "USA", "Expensive")));
        three.addEvent(vegas1);

        Event vegas2 = new Event();
        vegas2.setTitle("Helicopter Night Tour");
        vegas2.setStartDate(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        vegas2.setLocation(new LatLng(36.122180, -115.175091));
        vegas2.setCost(new Float(120.00));
        vegas2.setDescription("Was a little pricey but the view of the strip from above was incredible!");
        vegas2.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.vegas_helicopter))));
        vegas2.setTags(new ArrayList<String>(Arrays.asList("Tour", "USA", "Expensive", "Risk")));
        three.addEvent(vegas2);

        Event vegas3 = new Event();
        vegas3.setTitle("48th and Crepe");
        vegas3.setStartDate(new GregorianCalendar(2021, Calendar.OCTOBER, 10).getTime());
        vegas3.setLocation(new LatLng(36.101958, -115.173472));
        vegas3.setCost(new Float(25.00));
        vegas3.setDescription("Cute crepe place in the New York Casino. Fun but probably won't go back");
        vegas3.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.crepe))));
        vegas3.setTags(new ArrayList<String>(Arrays.asList("Food", "USA")));
        three.addEvent(vegas3);

        journals.add(three);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new MapsFragment()).commit();

        previousWindow = "Map";
        setTitle("Map");

        addButton = (ImageButton) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddDialog().show(
                        getSupportFragmentManager(), AddDialog.TAG);
            }
        });

        LinearLayout journalsButton = (LinearLayout) findViewById(R.id.journalsButton);
        journalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new JournalFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                setTitle("Journals");
                previousWindow = "Journals";
                hideBackButton();

                addButton = (ImageButton) findViewById(R.id.add_button);
                addButton.setVisibility(View.VISIBLE);
            }
        });

        LinearLayout mapButton = (LinearLayout) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MapsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                setTitle("Map");
                previousWindow = "Map";
                hideBackButton();

                addButton = (ImageButton) findViewById(R.id.add_button);
                addButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        popFromBackstack();
        hideBackButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    public List<Journal> getJournals() { return journals; }

    public void addJournal(Journal journal) { journals.add(journal); }

    public Journal getCurrentJournal() { return currentJournal; }

    public LatLng getCurrentLocation() { return currentLocation; }

    public void setCurrentLocation(LatLng newLocation) {
        this.currentLocation = newLocation;
    }

    public void doPositiveClick() {
        Fragment fragment = new JournalCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("journal").commit();

        addButton = (ImageButton) findViewById(R.id.add_button);
//        addButton.setVisibility(View.GONE);
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        Fragment fragment = new EventCreationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("event").commit();

        addButton = (ImageButton) findViewById(R.id.add_button);
//        addButton.setVisibility(View.GONE);
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void doNeutralClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public void jumpToMap() {
        Fragment fragment = new MapsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setTitle("Map");
        previousWindow = "Map";
    }

    public void popFromBackstack() {
        addButton = (ImageButton) findViewById(R.id.add_button);
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
        showBackButton();
    }

    public void inflateEventList() {
        Fragment eventFragment = new EventFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.event_list_container, eventFragment).commit();
    }



    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public Bitmap getMarkerBitmapFromView() {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.current_marker, null);

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
}
