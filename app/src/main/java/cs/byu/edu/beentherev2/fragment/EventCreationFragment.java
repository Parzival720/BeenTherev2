package cs.byu.edu.beentherev2.fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_creation, container, false);

        Button cancelButton = (Button)view.findViewById(R.id.create_event_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.popFromBackstack();
            }
        });

        //SET SPINNER ITEMS
        List<String> strings = new ArrayList<>();
        strings.add("Option 1");
        strings.add("Option 2");
        strings.add("Option 3");

        Spinner spinner = (Spinner) view.findViewById(R.id.create_event_journal_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, strings);
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
                //create the event
                Event event = new Event();
                event.setTitle(title.getText().toString());
                event.setDescription(description.getText().toString());

                //Figure out how to force formatting for both dates and cost

                MainActivity activity = (MainActivity) getActivity();
                activity.popFromBackstack();
            }
        });

        return view;
    }
}