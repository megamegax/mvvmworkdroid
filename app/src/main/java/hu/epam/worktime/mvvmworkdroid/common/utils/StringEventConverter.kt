package hu.epam.worktime.mvvmworkdroid.common.utils

import android.content.res.Resources
import hu.epam.worktime.mvvmworkdroid.R

/**


 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */

object StringEventConverter {
    fun eventNumberToStr(event: Int, resources: Resources): String {
        when (event) {
            1 -> return resources.getString(R.string.arrive)
            2 -> return resources.getString(R.string.leave)
            3 -> return resources.getString(R.string.dinner_begin)
            4 -> return resources.getString(R.string.dinner_end)
            5 -> return resources.getString(R.string.other_leave)
            6 -> return resources.getString(R.string.other_arrive)
            else -> return "unknown"
        }
    }

    fun strToEventNumber(eventText: String, resources: Resources): Int {
        if (eventText == resources.getString(R.string.arrive)) {
            return 1
        } else if (eventText == resources.getString(R.string.leave)) {
            return 2
        } else if (eventText == resources.getString(R.string.dinner_begin)) {
            return 3
        } else if (eventText == resources.getString(R.string.dinner_end)) {
            return 4
        } else if (eventText == resources.getString(R.string.other_leave)) {
            return 5
        } else if (eventText == resources.getString(R.string.other_arrive)) {
            return 6
        } else
            return 0
    }
}
