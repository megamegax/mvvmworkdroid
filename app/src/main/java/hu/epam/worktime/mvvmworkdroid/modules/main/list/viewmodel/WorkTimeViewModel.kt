package hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel

import android.databinding.Bindable
import android.support.v4.util.Pair
import android.view.View
import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

import org.threeten.bp.format.DateTimeFormatter

/**


 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

class WorkTimeViewModel(private val router: MainRouter) : ListItemViewModel() {

    lateinit var workTime: WorkTime

    val date: String
        @Bindable
        get() = workTime.date.format(DateTimeFormatter.ISO_LOCAL_DATE)

    val workHours: String
        @Bindable
        get() = workTime.nettoWork.format(DateTimeFormatter.ISO_LOCAL_TIME)

    fun onClick(v: View) {
        val dateText = Pair(v.findViewById(R.id.dateText), "dateText")
        val workHours = Pair(v.findViewById(R.id.workHoursText), "workHours")
        val card = Pair(v, "card")

        router.openDetails(arrayOf(card, dateText, workHours), workTime)
    }

    override fun getViewType(): Int {
        return 0
    }
}
