package hu.epam.worktime.mvvmworkdroid.modules.main.main.router;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import hu.epam.worktime.mvvmworkdroid.R;
import hu.epam.worktime.mvvmworkdroid.databinding.ActivityMainBinding;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.modules.details.view.DetailsActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveTimeActivity;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService;

public class MainActivity extends AppCompatActivity implements MainRouter {

    @Inject
    MainViewModel mainViewModel;

    @Inject
    CalculatorService calculatorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.setViewModel(mainViewModel);
        Toolbar toolbar = viewDataBinding.toolbar;
        TabLayout tabLayout = viewDataBinding.tabs;
        setSupportActionBar(toolbar);
        final ViewPager pager = viewDataBinding.viewpager;

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_stats)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_list)));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void inject() {
        MainActivityComponent component = MainActivityComponent.Injector.buildComponent(this);
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
            mainViewModel.refresh();
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

    @Override
    protected void onStart() {
        super.onStart();
        mainViewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainViewModel.onStop();
    }
}
