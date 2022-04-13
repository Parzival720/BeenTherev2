package cs.byu.edu.beentherev2.placeholder;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cs.byu.edu.beentherev2.databinding.FragmentEventBinding;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;
import cs.byu.edu.beentherev2.placeholder.PlaceholderContent.PlaceholderItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final ClickListener listener;

    public EventRecyclerViewAdapter(List<Event> items, ClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), listener);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());

        Date startDate = mValues.get(position).getStartDate();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Date endDate = mValues.get(position).getEndDate();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startDate);
        if (endDate == null || (startCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR) && startCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH) && startCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))) {
            String start = mValues.get(position).getPrettyStartDate();
            holder.mDatesView.setText(start);
        } else {
            String start = mValues.get(position).getPrettyStartDate();
            String end = mValues.get(position).getPrettyEndDate();
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final TextView mTitleView;
        public final TextView mDatesView;
        public final ImageView mPhotoView;
        public final TextView mLocView;
        public final TextView mDescriptionView;
        public final TextView mCostView;
        public final TextView mTagsView;

        public final LinearLayout mLocBox;
        private WeakReference<ClickListener> listenerRef;

        public Event mItem;

        public ViewHolder(FragmentEventBinding binding, ClickListener listener) {
            super(binding.getRoot());
            listenerRef = new WeakReference<>(listener);
            mTitleView = binding.eventTitle;
            mDatesView = binding.eventDates;
            mPhotoView = binding.eventPhoto;
            mLocView = binding.eventLocation;
            mDescriptionView = binding.eventDescription;
            mCostView = binding.eventCost;
            mTagsView = binding.eventTags;
            mLocBox = binding.eventLocationContainer;
            binding.getRoot().setOnClickListener(this);
            mLocBox.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == mLocBox.getId()) {
                listenerRef.get().onPositionClicked(getAbsoluteAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}