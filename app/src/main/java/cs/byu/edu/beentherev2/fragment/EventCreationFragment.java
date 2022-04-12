package cs.byu.edu.beentherev2.fragment;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventCreationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventCreationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventCreationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventCreation.
     */
    // TODO: Rename and change types and number of parameters
    public static EventCreationFragment newInstance(String param1, String param2) {
        EventCreationFragment fragment = new EventCreationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_creation, container, false);

        Button cancelButton = (Button)view.findViewById(R.id.create_event_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.popFromBackstack();
            }
        });

        //SET SPINNER ITEMS
        List<String> strings = new ArrayList<>();

        for (Journal journal : mainActivity.getJournals()) {
            strings.add(journal.getTitle());
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.create_event_journal_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, strings);
        spinner.setAdapter(adapter);

        EditText title = (EditText) view.findViewById(R.id.create_event_title);
        EditText description = (EditText) view.findViewById(R.id.create_event_description);
        EditText cost = (EditText) view.findViewById(R.id.create_event_cost);

        EditText startDate = (EditText) view.findViewById(R.id.create_event_startdate);
        EditText endDate = (EditText) view.findViewById(R.id.create_event_enddate);

        Button saveButton = (Button)view.findViewById(R.id.create_event_submit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                //create the event
                Event event = new Event();
                event.setTitle(title.getText().toString());
                event.setDescription(description.getText().toString());

                String beginDate = startDate.getText().toString();
                String stopDate = endDate.getText().toString();
                String costString = cost.getText().toString();

                //Get locations in the form of a LatLng
                LatLng location = null;

                Date start = null;
                Date end = null;
                Float eventCost = new Float(0);
                Boolean success = true;
                try {
                    start = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
                    end = new SimpleDateFormat("dd/MM/yyyy").parse(stopDate);
                    eventCost = Float.parseFloat(costString);
                }
                catch (NumberFormatException e) {
                    System.out.println(e);
                    Toast.makeText(context, "Invalid cost offered, unable to add journal", Toast.LENGTH_LONG).show();
                    success = false;
                }
                catch (Exception e) {
                    System.out.println(e);
                    Toast.makeText(context, "Invalid date offered, unable to add journal", Toast.LENGTH_LONG).show();
                    success = false;
                }

                if (success) {
                    event.setStartDate(start);
                    event.setEndDate(end);
                    event.setCost(eventCost);
                    //find way to parse from command line?
                    if (location == null) {
                        //set location to Provo (the Marb) if there is no location
                        location = new LatLng(40.24688, -111.64920);
                    }
                    event.setLocation(location);

                    //attach to a specific journal
                    for (Journal journal : mainActivity.getJournals()) {
                        if (journal.getTitle() == spinner.getSelectedItem().toString()) {
                            journal.addEvent(event);
                            Toast.makeText(context, String.format("Successfully added to %s", journal.getTitle()), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                mainActivity.popFromBackstack();
            }
        });

        return view;
    }
}