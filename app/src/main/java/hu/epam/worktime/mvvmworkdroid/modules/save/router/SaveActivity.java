package hu.epam.worktime.mvvmworkdroid.modules.save.router;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainBinding;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivitySaveTimeBinding;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.di.save.SaveActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel.SaveViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

import javax.inject.Inject;

public class SaveActivity extends AppCompatActivity implements SaveRouter {

    @Inject
    SaveViewModel saveViewModel;

    @Inject
    WorkServiceApi workServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ActivitySaveTimeBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_save_time);
        viewDataBinding.setViewModel(saveViewModel);

    }

    @Override
    public void goBack() {
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        saveViewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveViewModel.onStop();
    }

    private void inject() {
        SaveActivityComponent component = SaveActivityComponent.Injector.INSTANCE.buildComponent(this);
        component.inject(this);
    }
}
