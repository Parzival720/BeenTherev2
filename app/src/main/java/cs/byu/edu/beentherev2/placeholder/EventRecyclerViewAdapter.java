package cs.byu.edu.beentherev2.placeholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import cs.byu.edu.beentherev2.databinding.FragmentEventBinding;
import cs.byu.edu.beentherev2.databinding.FragmentJournalBinding;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;
import cs.byu.edu.beentherev2.placeholder.PlaceholderContent.PlaceholderItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;

    public EventRecyclerViewAdapter(List<Event> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(holder.mItem.getTitle());
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy");
        holder.mStartDate.setText(format.format(holder.mItem.getStartDate()));
        holder.mEndDate.setText(format.format(holder.mItem.getEndDate()));
        holder.mContentView.setText(holder.mItem.getDescription());
        holder.mCost.setText(holder.mItem.getCost().toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStartDate;
        public final TextView mEndDate;
        public final TextView mCost;

        //COST TO BE IMPLEMENTED
        //public final TextView mCost;
        public Event mItem;

        public ViewHolder(FragmentEventBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mStartDate = binding.startDateEvent;
            mEndDate = binding.endDateEvent;
            mCost = binding.cost;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}