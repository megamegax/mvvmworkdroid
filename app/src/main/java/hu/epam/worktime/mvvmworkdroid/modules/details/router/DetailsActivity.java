package hu.epam.worktime.mvvmworkdroid.modules.details.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import hu.epam.worktime.mvvmworkdroid.R;

public class DetailsActivity extends AppCompatActivity implements DetailsRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void goBack() {
        onBackPressed();
    }
}
