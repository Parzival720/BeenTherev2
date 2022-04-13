package cs.byu.edu.beentherev2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.placeholder.ClickListener;
import cs.byu.edu.beentherev2.placeholder.EventRecyclerViewAdapter;
import cs.byu.edu.beentherev2.placeholder.RecyclerItemClickListener;

/**
 * A fragment representing a list of Items.
 */
public class EventFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventFragment newInstance(int columnCount) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        MainActivity activity = (MainActivity) getActivity();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            List<Event> events = activity.getCurrentJournal().getEvents();
            if (events.size() == 0) {
                Event none = new Event();
                none.setTitle("Journal Created");
                none.setStartDate(new Date());
                none.setLocation(new LatLng(40.24688, -111.64920));
                none.setCost(new Float(0));
                none.setDescription("No events saved yet. Go make some memories!");
                none.setPhotos(new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.loading))));
                none.setTags(new ArrayList<String>(Arrays.asList("Empty", "Zip", "Nada")));
                events = Arrays.asList(none);
            }
            recyclerView.setAdapter(new EventRecyclerViewAdapter(events, new ClickListener() {
                @Override
                public void onPositionClicked(int position) {
                    LatLng newLocation = activity.getCurrentJournal().getEvents().get(position).getLocation();
                    activity.setCurrentLocation(newLocation);
                    activity.jumpToMap();
                }

                @Override
                public void onLongClicked(int position) {

                }
            }));

        }
        return view;
    }
}