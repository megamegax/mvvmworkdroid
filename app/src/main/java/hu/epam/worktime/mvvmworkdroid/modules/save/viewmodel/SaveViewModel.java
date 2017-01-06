package hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel;

import android.app.FragmentManager;
import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.common.utils.StringEventConverter;
import hu.epam.worktime.mvvmworkdroid.modules.save.model.SaveModel;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.Event;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */

public class SaveViewModel extends BaseObservable implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private FragmentManager fragmentManager;
    private SaveModel model;
    private SaveRouter router;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private Context context;
    private String event;

    public SaveViewModel(SaveModel model, SaveRouter router, Context context, FragmentManager fragmentManager) {
        this.model = model;
        this.router = router;
        this.fragmentManager = fragmentManager;
        this.context = context;
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();
    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void saveTime(View v) {
        int eventNumber = StringEventConverter.strToEventNumber(event, context.getResources());
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, currentTime);
        addTimeEvent(eventNumber, localDateTime);
    }

    private void addTimeEvent(int event, LocalDateTime localDateTime) {
        final Event sendableEvent = new Event(2, event, localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        new Thread(new Runnable() {
            @Override
            public void run() {
                model.sendEvent(sendableEvent);
                ((SaveActivity) (router)).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        router.goBack();
                    }
                });
            }
        }).start();
    }

    public void openDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, LocalDate.now().getYear(),
                LocalDate.now().getMonthValue() - 1,
                LocalDate.now().getDayOfMonth());

        datePickerDialog.setAccentColor(ContextCompat.getColor(context, R.color.colorAccent));
        datePickerDialog.show(fragmentManager, "DatePickerDialog");
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCurrentTime() {
        return currentTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getCurrentDate() {
        return currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        currentDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime
                .now().getSecond(), true);
        timePickerDialog.setAccentColor(ContextCompat.getColor(context, R.color.colorAccent));
        timePickerDialog.show(fragmentManager, "TimePickerDialog");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        currentTime = LocalTime.of(hourOfDay, minute, second);
        notifyChange();
    }
}
