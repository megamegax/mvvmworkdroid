package hu.epam.worktime.mvvmworkdroid.modules.main.main.router

import android.support.v4.util.Pair
import android.view.View
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

/**


 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

interface MainRouter {
    fun openDetails(sharedViews: Array<Pair<View, String>>, workTime: WorkTime)

    fun openNewEntry()
}
