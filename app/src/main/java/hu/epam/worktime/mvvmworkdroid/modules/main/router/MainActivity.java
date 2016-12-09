package hu.epam.worktime.mvvmworkdroid.modules.main.router;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ContentMainBinding;
import hu.epam.worktime.mvvmworkdroid.di.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.details.view.DetailsActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel.MainViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.save.view.SaveTimeActivity;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainRouter {

    private MainActivityComponent component;

    @Inject
    WorkServiceApi workServiceApi;

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ContentMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.content_main);
        viewDataBinding.setViewModel1(mainViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void inject() {
        component = MainActivityComponent.Injector.buildComponent(this);
        component.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openDetails(WorkTime workTime) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void openNewEntry() {
        Intent intent = new Intent(MainActivity.this, SaveTimeActivity.class);
        startActivity(intent);
    }

    public void addFabClicked(View view) {
        openNewEntry();
    }
}
