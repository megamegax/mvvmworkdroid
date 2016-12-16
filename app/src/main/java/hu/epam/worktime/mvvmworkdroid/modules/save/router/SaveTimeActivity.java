package hu.epam.worktime.mvvmworkdroid.modules.save.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

import javax.inject.Inject;

public class SaveTimeActivity extends AppCompatActivity implements SaveRouter {

    @Inject
    WorkServiceApi workServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void goBack() {
        this.finish();
    }
}
