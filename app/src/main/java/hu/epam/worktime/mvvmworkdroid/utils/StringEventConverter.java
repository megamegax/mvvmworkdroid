package hu.epam.worktime.mvvmworkdroid.utils;

import android.content.res.Resources;
import hu.epam.worktime.mvvmworkdroid.R;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */

public class StringEventConverter {
    public static String eventNumberToStr(int event, Resources resources) {
        switch (event) {
            case 1:
                return resources.getString(R.string.arrive);
            case 2:
                return resources.getString(R.string.leave);
            case 3:
                return resources.getString(R.string.dinner_begin);
            case 4:
                return resources.getString(R.string.dinner_end);
            case 5:
                return resources.getString(R.string.other_leave);
            case 6:
                return resources.getString(R.string.other_arrive);
            default:
                return "unknown";
        }
    }

    public static int strToEventNumber(String eventText, Resources resources) {
        if (eventText.equals(resources.getString(R.string.arrive))) {
            return 1;
        } else if (eventText.equals(resources.getString(R.string.leave))) {
            return 2;
        } else if (eventText.equals(resources.getString(R.string.dinner_begin))) {
            return 3;
        } else if (eventText.equals(resources.getString(R.string.dinner_end))) {
            return 4;
        } else if (eventText.equals(resources.getString(R.string.other_leave))) {
            return 5;
        } else if (eventText.equals(resources.getString(R.string.other_arrive))) {
            return 6;
        } else
            return 0;
    }
}
