package cs.byu.edu.beentherev2.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    private DatePicker datePicker;
    private Calendar calendar;
    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;
    private boolean startDateActive;
    private boolean endDateActive;

    private Date startDate;
    private Date endDate;

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

        calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        endYear = calendar.get(Calendar.YEAR);
        endMonth = calendar.get(Calendar.MONTH);
        endDay = calendar.get(Calendar.DAY_OF_MONTH);

        startDateActive = false;
        endDateActive = false;

        startDate = new Date();
        endDate = new Date();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    if (startDateActive) {
                        startDate = new GregorianCalendar(arg1, arg2, arg3).getTime();
                        startDateActive = false;
                        MainActivity mainActivity = (MainActivity) getActivity();
                        TextView startDateView = mainActivity.findViewById(R.id.create_event_startdate);
                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
                        startDateView.setText(dateFormat.format(startDate));
                    } else if (endDateActive) {
                        endDate = new GregorianCalendar(arg1, arg2, arg3).getTime();
                        endDateActive = false;
                        MainActivity mainActivity = (MainActivity) getActivity();
                        TextView endDateView = mainActivity.findViewById(R.id.create_event_enddate);
                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
                        endDateView.setText(dateFormat.format(endDate));
                    }
                }
            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_creation, container, false);

        TextView startDatePicker = (TextView) view.findViewById(R.id.create_event_startdate);
        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateActive = true;
                Dialog dialog = new DatePickerDialog(getContext(),
                        myDateListener, startYear, startMonth, startDay);
                dialog.show();
            }
        });

        TextView endDatePicker = (TextView) view.findViewById(R.id.create_event_enddate);
        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateActive = true;
                Dialog dialog = new DatePickerDialog(getContext(),
                        myDateListener, endYear, endMonth, endDay);
                dialog.show();
            }
        });


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

        Button saveButton = (Button)view.findViewById(R.id.create_event_submit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                //create the event
                Event event = new Event();
                event.setTitle(title.getText().toString());
                event.setDescription(description.getText().toString());

                String costString = cost.getText().toString();

                //Get locations in the form of a LatLng
                LatLng location = null;

                Float eventCost = new Float(0);
                Boolean success = true;
                try {
                    eventCost = Float.parseFloat(costString);
                }
                catch (NumberFormatException e) {
                    System.out.println(e);
                    Toast.makeText(context, "Invalid cost offered, unable to add journal", Toast.LENGTH_LONG).show();
                    success = false;
                }


                if (success) {
                    event.setStartDate(startDate);
                    event.setEndDate(endDate);
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