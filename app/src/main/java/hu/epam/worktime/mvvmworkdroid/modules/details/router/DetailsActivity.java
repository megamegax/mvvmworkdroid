package hu.epam.worktime.mvvmworkdroid.modules.details.router;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import javax.inject.Inject;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityDetailsBinding;
import hu.epam.worktime.mvvmworkdroid.di.details.DetailsActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

public class DetailsActivity extends AppCompatActivity implements DetailsRouter {

    public static final String WORK_ITEM = "workItem";
    @Inject
    DetailsViewModel detailsViewModel;

    @Inject
    Gson gson;
    private WorkTime workTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ActivityDetailsBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewDataBinding.setViewModel(detailsViewModel);
        loadExtras();
        detailsViewModel.setSelectedWorkTime(workTime);
    }

    private void inject() {
        DetailsActivityComponent component = DetailsActivityComponent.Injector.buildComponent(this);
        component.inject(this);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    public void loadExtras() {
        Intent intent = getIntent();
        String jsonWorkTime = intent.getStringExtra(WORK_ITEM);
        workTime = gson.fromJson(jsonWorkTime, WorkTime.class);
    }
}
