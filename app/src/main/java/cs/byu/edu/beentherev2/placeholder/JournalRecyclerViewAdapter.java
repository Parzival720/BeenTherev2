package cs.byu.edu.beentherev2.placeholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Journal mItem;

        public ViewHolder(FragmentJournalBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface onJournalListener {
        void onJournalClick(int position);
    }
}