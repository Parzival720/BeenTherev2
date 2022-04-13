package cs.byu.edu.beentherev2.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Journal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JournalCreationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JournalCreationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatePicker datePicker;
    private Calendar calendar;
    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;
    private boolean startDateActive;
    private boolean endDateActive;

    private Date startDate;
    private Date endDate;

    public static String TAG = "DatePickerDialog";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JournalCreationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JournalCreationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalCreationFragment newInstance(String param1, String param2) {
        JournalCreationFragment fragment = new JournalCreationFragment();
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
                        TextView startDateView = mainActivity.findViewById(R.id.create_journal_start_date);
                        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
                        startDateView.setText(dateFormat.format(startDate));
                    } else if (endDateActive) {
                        endDate = new GregorianCalendar(arg1, arg2, arg3).getTime();
                        endDateActive = false;
                        MainActivity mainActivity = (MainActivity) getActivity();
                        TextView endDateView = mainActivity.findViewById(R.id.create_journal_end_date);
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
        View view = inflater.inflate(R.layout.fragment_journal_creation, container, false);

        TextView startDatePicker = (TextView) view.findViewById(R.id.create_journal_start_date);
        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDateActive = true;
                Dialog dialog = new DatePickerDialog(getContext(),
                        myDateListener, startYear, startMonth, startDay);
                dialog.show();
            }
        });

        TextView endDatePicker = (TextView) view.findViewById(R.id.create_journal_end_date);
        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateActive = true;
                Dialog dialog = new DatePickerDialog(getContext(),
                        myDateListener, endYear, endMonth, endDay);
                dialog.show();
            }
        });


        Button cancelButton = (Button)view.findViewById(R.id.create_journal_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.popFromBackstack();
            }
        });

        EditText title = (EditText) view.findViewById(R.id.create_journal_title);
        EditText description = (EditText) view.findViewById(R.id.create_journal_description);

        Button saveButton = (Button)view.findViewById(R.id.create_journal_submit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                //create Journal object and send back to main to be added to journals array
                String journalTitle = title.getText().toString();
                String journalDescription = description.getText().toString();
                Journal journal = new Journal();
                journal.setTitle(journalTitle);
                journal.setDescription(journalDescription);
//                String beginDate = startDate.getText().toString();
//                String stopDate = endDate.getText().toString();
//
//                Date start = null;
//                Date end = null;
//                Boolean success = true;
//                try {
//                    start = new SimpleDateFormat("dd/MM/yyyy").parse(beginDate);
//                    end = new SimpleDateFormat("dd/MM/yyyy").parse(stopDate);
//                }
//                catch (Exception e) {
//                    System.out.println(e);
//                    Toast.makeText(context, "Invalid date offered, unable to add journal", Toast.LENGTH_LONG).show();
//                    success = false;
//                }

                journal.setStartDate(startDate);
                journal.setEndDate(endDate);
                Toast.makeText(context, String.format("Successfully added %s", journal.getTitle()), Toast.LENGTH_LONG).show();
                mainActivity.addJournal(journal);
                mainActivity.popFromBackstack();
            }
        });
        return view;
    }
}