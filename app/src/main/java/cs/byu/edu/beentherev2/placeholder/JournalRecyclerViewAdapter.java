package cs.byu.edu.beentherev2.placeholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;
import cs.byu.edu.beentherev2.placeholder.PlaceholderContent.PlaceholderItem;
import cs.byu.edu.beentherev2.databinding.FragmentJournalBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class JournalRecyclerViewAdapter extends RecyclerView.Adapter<JournalRecyclerViewAdapter.ViewHolder> {

    private final List<Journal> mValues;

    public JournalRecyclerViewAdapter(List<Journal> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentJournalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());
        String start = mValues.get(position).getPrettyStartDate();
        String end = mValues.get(position).getPrettyEndDate();
        holder.mDatesView.setText(start + " - " + end);
        holder.mPhotoView.setImageResource(mValues.get(position).getPhoto());
        List<Event> events = mValues.get(position).getEvents();
        holder.mEventsView.setText(events.size()+"");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final TextView mDatesView;
        public final TextView mEventsView;
        public final ImageView mPhotoView;
        public Journal mItem;

        public ViewHolder(FragmentJournalBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.journalTitle;
            mDescriptionView = binding.journalDescription;
            mDatesView = binding.journalDates;
            mPhotoView = binding.journalPhoto;
            mEventsView = binding.journalEvents;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }

    public interface onJournalListener {
        void onJournalClick(int position);
    }
}