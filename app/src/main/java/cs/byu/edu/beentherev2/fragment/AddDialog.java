package cs.byu.edu.beentherev2.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;

public class AddDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog askToAdd = new AlertDialog.Builder(requireContext(), R.style.MyDialogTheme)
                .setMessage(getString(R.string.add_question))
                .setPositiveButton(getString(R.string.journal), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity)getActivity()).doPositiveClick();
                    }
                })
                .setNegativeButton(getString(R.string.event), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity)getActivity()).doNegativeClick();
                    }
                })
                .setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity)getActivity()).doNeutralClick();
                    }
                })
                .create();

        return askToAdd;
    }

    public static String TAG = "AddDialog";
}