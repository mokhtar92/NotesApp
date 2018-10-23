package iti.gov.eg.livedata_mvvm_room;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.database.DbNote;

import static iti.gov.eg.livedata_mvvm_room.Constants.DISPLAYED_DATE_FORMAT_PATTERN;
import static iti.gov.eg.livedata_mvvm_room.Constants.STORED_DATE_FORMAT_PATTERN;

public class HelperClass {

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String formatDateTimeFromServer(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(STORED_DATE_FORMAT_PATTERN, Locale.getDefault());
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat(DISPLAYED_DATE_FORMAT_PATTERN, Locale.getDefault());
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }
        return "";
    }
}