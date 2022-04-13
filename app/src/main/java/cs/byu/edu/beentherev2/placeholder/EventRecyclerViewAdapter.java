package cs.byu.edu.beentherev2.placeholder;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        String start = mValues.get(position).getPrettyStartDate();
        String end = mValues.get(position).getPrettyEndDate();
        if (end == null) {
            holder.mDatesView.setText(start);
        } else {
            holder.mDatesView.setText(start + " - " + end);
        }
        holder.mLocView.setText(mValues.get(position).getPrettyLocation());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());
        float cost = mValues.get(position).getCost();
        if (cost == 0) {
            holder.mCostView.setText("FREE");
        } else {
            holder.mCostView.setText("$" + cost);
        }
        String tagList = String.join(", ", mValues.get(position).getTags());
        holder.mTagsView.setText(tagList);
        holder.mPhotoView.setImageResource(mValues.get(position).getPhotos().get(0).intValue());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDatesView;
        public final ImageView mPhotoView;
        public final TextView mLocView;
        public final TextView mDescriptionView;
        public final TextView mCostView;
        public final TextView mTagsView;

        public Event mItem;

        public ViewHolder(FragmentEventBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.eventTitle;
            mDatesView = binding.eventDates;
            mPhotoView = binding.eventPhoto;
            mLocView = binding.eventLocation;
            mDescriptionView = binding.eventDescription;
            mCostView = binding.eventCost;
            mTagsView = binding.eventTags;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}