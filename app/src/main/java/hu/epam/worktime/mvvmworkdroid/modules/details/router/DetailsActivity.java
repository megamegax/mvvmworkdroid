package hu.epam.worktime.mvvmworkdroid.modules.details.router;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityDetailsBinding;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivitySaveTimeBinding;
import hu.epam.worktime.mvvmworkdroid.di.details.DetailsActivityComponent;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel;

public class DetailsActivity extends AppCompatActivity implements DetailsRouter {

    @Inject
    DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ActivityDetailsBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewDataBinding.setViewModel(detailsViewModel);

    }

    private void inject() {
        DetailsActivityComponent component = DetailsActivityComponent.Injector.buildComponent(this);
        component.inject(this);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }
}
