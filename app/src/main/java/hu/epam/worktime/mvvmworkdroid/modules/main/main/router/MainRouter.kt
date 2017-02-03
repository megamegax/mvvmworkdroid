package hu.epam.worktime.mvvmworkdroid.modules.main.main.router

import android.view.View
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

/**


 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

interface MainRouter {
    fun openDetails(sharedView: View, workTime: WorkTime)

    fun openNewEntry()
}
