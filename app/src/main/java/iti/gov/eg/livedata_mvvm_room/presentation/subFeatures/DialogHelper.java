package iti.gov.eg.livedata_mvvm_room.presentation.subFeatures;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import iti.gov.eg.livedata_mvvm_room.R;

public class DialogHelper {

    public static AlertDialog.Builder getDialogBuilder(final Context context) {
        return new AlertDialog.Builder(context)
                .setCancelable(false)
                .setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }
}