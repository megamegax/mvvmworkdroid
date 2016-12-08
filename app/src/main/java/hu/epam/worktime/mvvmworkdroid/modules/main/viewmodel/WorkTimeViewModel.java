package hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel;

import android.databinding.Bindable;
import android.view.View;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

import org.threeten.bp.format.DateTimeFormatter;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class WorkTimeViewModel extends ListItemViewModel {
    private WorkTime workTime;
    private MainRouter router;

    public WorkTimeViewModel(MainRouter router) {
        this.router = router;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    @Bindable
    public String getTitle() {
        return workTime.getDate().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public void onClick(View v) {
        router.openDetails(workTime);
        //TODO:Check it out.
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
