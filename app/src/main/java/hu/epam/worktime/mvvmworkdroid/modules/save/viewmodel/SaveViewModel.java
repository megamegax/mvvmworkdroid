package hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel;

import hu.epam.worktime.mvvmworkdroid.modules.save.model.SaveModel;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveRouter;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */

public class SaveViewModel {
    private final SaveModel model;
    private final SaveRouter router;

    public SaveViewModel(SaveModel model, SaveRouter router) {
        this.model = model;
        this.router = router;
    }

    public void onStart() {

    }

    public void onStop() {

    }
}
