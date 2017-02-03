package hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel

import android.databinding.Bindable
import android.view.View
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
        router.openDetails(v, workTime)
    }

    override fun getViewType(): Int {
        return 0
    }
}
