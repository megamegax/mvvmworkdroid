package hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel

import android.app.FragmentManager
import android.content.Context
import android.databinding.BaseObservable
import android.support.v4.content.ContextCompat
import android.view.View

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

import hu.epam.worktime.mvvmworkdroid.R
import hu.epam.worktime.mvvmworkdroid.common.utils.StringEventConverter
import hu.epam.worktime.mvvmworkdroid.modules.save.model.SaveModel
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveRouter
import hu.epam.worktime.mvvmworkdroid.modules.services.models.Event

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */

class SaveViewModel(private val model: SaveModel, private val router: SaveRouter, private val context: Context, private val fragmentManager: FragmentManager) : BaseObservable(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var currentDate: LocalDate? = null
    private var currentTime: LocalTime? = null
    var event: String? = null

    init {
        currentDate = LocalDate.now()
        currentTime = LocalTime.now()
    }

    fun onStart() {

    }

    fun onStop() {

    }

    fun saveTime(v: View) {
        val eventNumber = StringEventConverter.strToEventNumber(event!!, context.resources)
        val localDateTime = LocalDateTime.of(currentDate!!, currentTime!!)
        addTimeEvent(eventNumber, localDateTime)
    }

    private fun addTimeEvent(event: Int, localDateTime: LocalDateTime) {
        val sendableEvent = Event(2, event, localDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
        Thread(Runnable {
            model.sendEvent(sendableEvent)
            (router as SaveActivity).runOnUiThread { router.goBack() }
        }).start()
    }

    fun openDatePickerDialog(v: View) {
        val datePickerDialog = DatePickerDialog.newInstance(this, LocalDate.now().year,
                LocalDate.now().monthValue - 1,
                LocalDate.now().dayOfMonth)

        datePickerDialog.accentColor = ContextCompat.getColor(context, R.color.colorAccent)
        datePickerDialog.show(fragmentManager, "DatePickerDialog")
    }

    fun getCurrentTime(): String {
        return currentTime!!.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    fun getCurrentDate(): String {
        return currentDate!!.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        currentDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
        val timePickerDialog = TimePickerDialog.newInstance(this, LocalTime.now().hour, LocalTime.now().minute, LocalTime
                .now().second, true)
        timePickerDialog.accentColor = ContextCompat.getColor(context, R.color.colorAccent)
        timePickerDialog.show(fragmentManager, "TimePickerDialog")
    }

    override fun onTimeSet(view: RadialPickerLayout, hourOfDay: Int, minute: Int, second: Int) {
        currentTime = LocalTime.of(hourOfDay, minute, second)
        notifyChange()
    }
}
